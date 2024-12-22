package Parcel_Processing_System;

public class Parcel {
    //fields
    private String parcelID;
    private int daysInDepot;
    private double weight;
    private String dimensions;
    private String status;

    //Constructor
    public Parcel (String parcelID, int daysInDepot, double weight, String dimensions,String status){
        this.parcelID = parcelID;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.dimensions = dimensions;
        this.status = status;
    }
    //Getters and Setters
    public String getParcelID(){
        return parcelID;
    }
    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }
    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
   // toString method

   public String toString() {
       return "Parcel: ID= " + parcelID + ", DaysInDepot= " + daysInDepot +
               ", Weight= " + weight + ", Dimensions= " + dimensions +
               ", Status= " + status;
   }




}


