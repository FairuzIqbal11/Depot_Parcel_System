package Parcel_Processing_System;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private ParcelMap parcelMap;
    private QueofCustomers customerQueue;
    private List<Parcel> collectedParcels;
    private double totalFeesCollected;
    private List<Observer> observers = new ArrayList<>();

    public Manager() {
        this.parcelMap = new ParcelMap();
        this.customerQueue = new QueofCustomers();
        this.collectedParcels = new ArrayList<>();
        this.totalFeesCollected = 0;
    }

    public void initializeSystem(String parcelFile, String customerFile) {
        loadParcelsFromFile(parcelFile);
        loadCustomersFromFile(customerFile);
        notifyObservers(); // Notify observers after system initialization
    }

    private void loadParcelsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String parcelID = parts[0].trim();
                    int daysInDepot = Integer.parseInt(parts[1].trim());
                    double weight = Double.parseDouble(parts[2].trim());
                    String dimensions = parts[3].trim() + "x" + parts[4].trim() + "x" + parts[5].trim();
                    Parcel parcel = new Parcel(parcelID, daysInDepot, weight, dimensions, "waiting");
                    parcelMap.addParcel(parcel);
                    Log.getInstance().addLog("Loaded parcel: " + parcel);
                } else {
                    Log.getInstance().addLog("Invalid Parcel Data: " + line);
                }
            }
            notifyObservers(); // Notify after all parcels are loaded
        } catch (IOException e) {
            Log.getInstance().addLog("Error reading Parcel file: " + e.getMessage());
        }
    }


    private void loadCustomersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int sequenceNumber = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String parcelID = parts[1].trim();
                    Customer customer = new Customer(sequenceNumber++, name, parcelID);
                    customerQueue.addCustomer(customer);
                    Log.getInstance().addLog("Loaded customer: " + customer);
                } else {
                    Log.getInstance().addLog("Invalid Customer Data: " + line);
                }
            }
            notifyObservers(); // Notify after all customers are loaded
        } catch (IOException e) {
            Log.getInstance().addLog("Error reading Customer file: " + e.getMessage());
        }
    }

    public void processCustomers() {
        Worker worker = new Worker();

        // Process each customer in the queue
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.removeCustomer();
            Parcel parcel = parcelMap.getParcel(customer.getParcelID());

            if (parcel != null && "waiting".equals(parcel.getStatus())) {
                // Process the parcel
                double fee = worker.processCustomer(customer, parcelMap);
                parcel.setStatus("collected");
                parcelMap.updateParcel(parcel); // Ensure the map is updated
                collectedParcels.add(parcel);
                totalFeesCollected += fee;
                notifyObservers(); // Notify observers after processing each customer
            }
        }

        // Log remaining uncollected parcels (those still marked as "waiting")
        for (Parcel parcel : parcelMap.getAllParcels()) {
            if ("waiting".equals(parcel.getStatus())) {
                Log.getInstance().addLog("Uncollected Parcel: " + parcel);
            }
        }

        Log.getInstance().addLog("All customers have been processed.");
        notifyObservers(); // Notify observers after processing all customers
    }

    private void validateUnlinkedParcels() {
        for (Parcel parcel : parcelMap.getAllParcels()) {
            boolean isLinked = false;
            for (Customer customer : customerQueue.getCustomerQueue()) {
                if (customer.getParcelID().equals(parcel.getParcelID())) {
                    isLinked = true;
                    break;
                }
            }
            if (!isLinked && "waiting".equals(parcel.getStatus())) {
                Log.getInstance().addLog("Parcel not linked to any customer: " + parcel.getParcelID());
                notifyObservers(); // Notify observers for each unlinked parcel
            }
        }
    }

    public void generateReport() {
        StringBuilder reportContent = new StringBuilder("Report:\n\n");

        // Collected Parcels
        reportContent.append("Collected Parcels:\n");
        for (Parcel parcel : collectedParcels) {
            reportContent.append(parcel).append("\n");
        }

        // Uncollected Parcels
        reportContent.append("\nUncollected Parcels:\n");
        for (Parcel parcel : parcelMap.getAllParcels()) {
            if ("waiting".equals(parcel.getStatus())) {
                reportContent.append(parcel).append("\n");
            }
        }

        // Summary Section
        reportContent.append("\nSummary:\n")
                .append("Total Fees Collected: ").append(totalFeesCollected).append("\n")
                .append("Parcels in Depot > 5 Days: ").append(countParcelsInDepot(5)).append("\n");

        // Write report to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Report.txt"))) {
            writer.write(reportContent.toString());
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
        notifyObservers(); // Notify observers after generating the report
    }

    private int countParcelsInDepot(int days) {
        int count = 0;
        for (Parcel parcel : parcelMap.getAllParcels()) {
            if (parcel.getDaysInDepot() > days && "waiting".equals(parcel.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public ParcelMap getParcelMap() {
        return this.parcelMap;
    }

    public QueofCustomers getCustomerQueue() {
        return this.customerQueue;
    }

    public interface Observer {
        void update();
    }

    // Register an observer
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    // Remove an observer
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    // Notify all observers of changes
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        // Register a LogObserver to confirm the notification mechanism works
        manager.registerObserver(new MockObserver());
        manager.registerObserver(new LogObserver());

        System.out.println("Initializing the system...");
        manager.initializeSystem("src/Parcels.csv", "src/Custs.csv");

        System.out.println("\nValidating unlinked parcels...");
        manager.validateUnlinkedParcels();

        System.out.println("\nProcessing customers...");
        manager.processCustomers();

        System.out.println("\nLogs:");
        System.out.println(Log.getInstance().getLogData());

        manager.generateReport();
        System.out.println("Report generated successfully!");

    }
}
