package Parcel_Processing_System;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // Test Parcel Class
        System.out.println("=== Testing Parcel Class ===");
        Parcel parcel = new Parcel("P001", 5, 10.0, "10x5x5", "waiting");
        System.out.println(parcel);

        // Test Customer Class
        System.out.println("\n=== Testing Customer Class ===");
        Customer customer = new Customer(1, "John Doe", "P001");
        System.out.println(customer);

        // Test QueofCustomers Class
        System.out.println("\n=== Testing QueofCustomers Class ===");
        QueofCustomers queue = new QueofCustomers();
        queue.addCustomer(customer);
        queue.addCustomer(new Customer(2, "Jane Smith", "P002"));
        System.out.println(queue.getQueueDetails());
        queue.removeCustomer();
        System.out.println("After removal: " + queue.getQueueDetails());

        // Test ParcelMap Class
        System.out.println("\n=== Testing ParcelMap Class ===");
        ParcelMap parcelMap = new ParcelMap();
        parcelMap.addParcel(parcel);
        parcelMap.addParcel(new Parcel("P002", 3, 5.0, "8x4x6", "waiting"));
        System.out.println(parcelMap.getParcelDetails());

        // Test Sorting by Customer Surname
        System.out.println("\n=== Testing Sorting by Customer Surname ===");
        queue.addCustomer(new Customer(3, "Michael Brown", "P002"));
        queue.addCustomer(new Customer(4, "Alice Johnson", "P003"));
        System.out.println("Sorted Parcels:");
        for (Parcel p : parcelMap.getSortedParcelsByCustomerSurname(queue)) {
            System.out.println(p);
        }

        // Test Worker Class
        System.out.println("\n=== Testing Worker Class ===");
        Worker worker = new Worker();
        worker.processCustomer(customer, parcelMap); // Customer John Doe collecting parcel P001
        System.out.println("Updated ParcelMap after processing:");
        System.out.println(parcelMap.getParcelDetails());
        System.out.println("Logs:");
        System.out.println(Log.getInstance().getLogData());

        // Test Log Class
        System.out.println("\n=== Testing Log Writing to File ===");
        Log.getInstance().writeLogToFile("log.txt");
        System.out.println("Log file written successfully!");

        // Test Manager Integration
        System.out.println("\n=== Testing Manager Class ===");
        Manager manager = new Manager();
        manager.initializeSystem("src/Parcels.csv", "src/Custs.csv");
        System.out.println("Loaded Parcels:");
        System.out.println(manager.getParcelMap().getParcelDetails());
        System.out.println("Loaded Customers:");
        System.out.println(manager.getCustomerQueue().getQueueDetails());
        manager.processCustomers();
        System.out.println("Logs after processing all customers:");
        System.out.println(Log.getInstance().getLogData());
        manager.generateReport();
        System.out.println("Report generated successfully!");

        // Step 2: Manually create parcels (linked and unlinked)
        Parcel parcel1 = new Parcel("X001", 3, 2.0, "3x4x5", "waiting"); // Linked parcel
        Parcel parcel2 = new Parcel("X002", 7, 3.0, "2x3x8", "waiting"); // Unlinked parcel
        Parcel parcel3 = new Parcel("X003", 5, 1.5, "4x4x4", "waiting"); // Linked parcel
        Parcel parcel4 = new Parcel("X004", 10, 5.0, "5x5x5", "waiting"); // Unlinked parcel

        // Add parcels to the ParcelMap
        manager.getParcelMap().addParcel(parcel1);
        manager.getParcelMap().addParcel(parcel2);
        manager.getParcelMap().addParcel(parcel3);
        manager.getParcelMap().addParcel(parcel4);

        // Step 3: Manually create customers (some linked, some with invalid parcel IDs)
        Customer customer1 = new Customer(1, "John Doe", "X001"); // Valid link
        Customer customer2 = new Customer(2, "Jane Smith", "X003"); // Valid link
        Customer customer3 = new Customer(3, "Alice Brown", "X999"); // Invalid parcel ID

        // Add customers to the queue
        manager.getCustomerQueue().addCustomer(customer1);
        manager.getCustomerQueue().addCustomer(customer2);
        manager.getCustomerQueue().addCustomer(customer3);

        // Step 4: Process customers
        manager.processCustomers();

        // Step 5: Generate the report
        manager.generateReport();

        // Step 6: Print logs and outputs for validation
        System.out.println("\nLogs:");
        System.out.println(Log.getInstance().getLogData());

        System.out.println("\nReport:");
        System.out.println("Generated report available in Report.txt");

        System.out.println("Initial Customer Queue:");
        System.out.println(manager.getCustomerQueue().getQueueDetails());

        System.out.println("\nProcessing customers...");
        manager.processCustomers();

        System.out.println("\nFinal Customer Queue (should be empty):");
        System.out.println(manager.getCustomerQueue().getQueueDetails());

    }

}


