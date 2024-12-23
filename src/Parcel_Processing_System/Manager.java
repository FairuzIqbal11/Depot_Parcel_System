package Parcel_Processing_System;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class Manager {
        private ParcelMap parcelMap;
        private QueofCustomers customerQueue;

        public Manager() {
            this.parcelMap = new ParcelMap();
            this.customerQueue = new QueofCustomers();
        }

        // Initialize the system by reading files
        public void initializeSystem(String parcelFile, String customerFile) {
            loadParcelsFromFile(parcelFile);
            loadCustomersFromFile(customerFile);
        }

        // Load parcels from the CSV file
        private void loadParcelsFromFile(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) { // Ensure correct format
                        String parcelID = parts[0].trim();
                        int daysInDepot = Integer.parseInt(parts[1].trim());
                        double weight = Double.parseDouble(parts[2].trim());
                        String dimensions = parts[3].trim() + "x" + parts[4].trim() + "x" + parts[5].trim();

                        Parcel parcel = new Parcel(parcelID, daysInDepot, weight, dimensions, "waiting");
                        parcelMap.addParcel(parcel);
                    } else {
                        Log.getInstance().addLog("Invalid Parcel Data: " + line + " (Incorrect format)");
                    }
                }
            } catch (IOException e) {
                Log.getInstance().addLog("Error reading Parcel file: " + e.getMessage());
            }
        }


        // Load customers from the CSV file
        private void loadCustomersFromFile(String filePath) {
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int sequenceNumber = 1; // Assign sequential numbers
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) { // Ensure correct format
                        String name = parts[0].trim();
                        String parcelID = parts[1].trim();

                        Customer customer = new Customer(sequenceNumber++, name, parcelID);
                        customerQueue.addCustomer(customer);
                    } else {
                        Log.getInstance().addLog("Invalid Customer Data: " + line + " (Incorrect format)");
                    }
                }
            } catch (IOException e) {
                Log.getInstance().addLog("Error reading Customer file: " + e.getMessage());
            }
        }


        // Process all customers
        public void processCustomers() {
            Worker worker = new Worker();
            while (!customerQueue.isEmpty()) {
                Customer customer = customerQueue.removeCustomer();
                worker.processCustomer(customer, parcelMap);
            }
            Log.getInstance().addLog("All customers have been processed.");
        }
        public ParcelMap getParcelMap() {
            return parcelMap;
        }

        public QueofCustomers getCustomerQueue() {
            return customerQueue;
        }


        // Main method to start the system
        public static void main(String[] args) {
            Manager manager = new Manager();
            System.out.println("Initializing the system...");

            manager.initializeSystem("src/Parcels.csv", "src/Custs.csv");


            System.out.println("\nLoaded Parcels:");
            System.out.println(manager.getParcelMap().getParcelDetails());

            System.out.println("\nLoaded Customers:");
            System.out.println(manager.getCustomerQueue().getQueueDetails());

            System.out.println("\nProcessing customers...");
            manager.processCustomers();

            System.out.println("\nLogs:");
            System.out.println(Log.getInstance().getLogData());
        }
    }



