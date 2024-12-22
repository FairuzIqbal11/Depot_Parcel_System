package Parcel_Processing_System;

public class Worker {

    public void processCustomer (Customer customer, ParcelMap parcelMap){
        Parcel parcel = parcelMap.getParcel(customer.getParcelID());
        if (parcel != null) {
            double parcelFee = calculateFee(parcel);
            parcelMap.removeParcel(customer.getParcelID());
            System.out.println("Processed Customer: " + customer.getName() + ", Collection Fee: " + parcelFee);
        } else{
            System.out.println(" Parcel is not found for customer " + customer.getName());
        }
    }

    // To calculate the collection fee for a parcel
    private double calculateFee(Parcel parcel){
        return parcel.getWeight() * 2 + parcel.getDaysInDepot() * 0.5;

    }
}
