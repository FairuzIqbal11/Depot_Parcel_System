package Parcel_Processing_System;
import java.util.LinkedList;
import java.util.Queue;

public class QueofCustomers {
    private Queue<Customer> customerQueue;

    // Constructor
    public QueofCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    // To add a customer to the queue
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        Log.getInstance().addLog("Customer added to queue: " + customer.getName() + " with Parcel ID: " + customer.getParcelID());
    }

    // To remove a customer from the queue
    public Customer removeCustomer() {
        if (!customerQueue.isEmpty()) {
            Customer removedCustomer = customerQueue.remove();
            Log.getInstance().addLog("Customer removed from queue: " + removedCustomer.getName() + " with Parcel ID: " + removedCustomer.getParcelID());
            return removedCustomer;
        } else {
            Log.getInstance().addLog("Attempted to remove a customer, but the queue was empty.");
            return null;
        }
    }

    // Checking if the queue is empty
    public boolean isEmpty() {
        boolean empty = customerQueue.isEmpty();
        Log.getInstance().addLog("Checked if queue is empty: " + empty);
        return empty;
    }

    // To know the queue size
    public int getSize() {
        int size = customerQueue.size();
        Log.getInstance().addLog("Checked queue size: " + size);
        return size;
    }

    // To display all the customers in the queue
    public String getQueueDetails() {
        if (customerQueue.isEmpty()) {
            Log.getInstance().addLog("Attempted to display queue details, but the queue is empty.");
            return "The customer queue is empty.";
        }
        StringBuilder queueDetails = new StringBuilder("Customer Queue:\n");
        for (Customer customer : customerQueue) {
            queueDetails.append(customer).append("\n");
        }
        Log.getInstance().addLog("Displayed customer queue details.");
        return queueDetails.toString();
    }
    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }

}

