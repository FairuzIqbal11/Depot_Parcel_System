package Parcel_Processing_System;
import java.util.LinkedList;
import java.util.Queue;

public class QueofCustomers {
    private Queue<Customer> customerQueue;

    //Constructor
    public QueofCustomers(){
        this.customerQueue = new LinkedList<>();
    }

    // To add a customer to the queue
    public void addCustomer(Customer customer){
        customerQueue.add(customer);
    }

    // To remove a customer from the queue
    public Customer removeCustomer() {
        if (!customerQueue.isEmpty()) {
            return customerQueue.remove();
        } else {
            return null;
        }
    }


    // Checking if the queue is empty
    public boolean isEmpty(){
        return customerQueue.isEmpty();
    }

    // To know the queue size
    public int getSize(){
        return customerQueue.size();
    }

    // To display all the customers in the queue
    public String getQueueDetails(){
        String queueDetails= "Customer Queue: \n";
        for (Customer customer : customerQueue){
            queueDetails += customer + "\n";
        }
        return queueDetails;
    }
}
