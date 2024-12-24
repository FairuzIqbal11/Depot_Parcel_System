package Parcel_Processing_System;

public class Customer {
    private int sequenceNumber;
    private String name;
    private String parcelID;

    // Constructor
    public Customer(int sequenceNumber, String name, String parcelID) {
        this.sequenceNumber = sequenceNumber;
        this.name = name;
        this.parcelID = parcelID;
    }

    // Getters and Setters
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    // toString method
    @Override
    public String toString() {
        return "Customer SequenceNumber= " + sequenceNumber + ", CustomerName=" + name + ", ParcelID= " + parcelID;
    }
}


