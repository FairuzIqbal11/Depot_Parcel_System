package Parcel_Processing_System;

public class Worker {

    public double processCustomer(Customer customer, ParcelMap parcelMap) {
        Parcel parcel = parcelMap.getParcel(customer.getParcelID());
        if (parcel != null) {
            double fee = calculateFee(parcel);
            parcel.setStatus("collected"); // Update the status to collected
            parcelMap.updateParcel(parcel); // Ensure the updated parcel is saved back
            Log.getInstance().addLog("Processed Customer: " + customer.getName() + ", Fee: " + fee);
            return fee;
        } else {
            Log.getInstance().addLog("Parcel not found for customer: " + customer.getName());
            return 0; // No fee if the parcel is not found
        }
    }


    public double calculateFee(Parcel parcel) {
        double fee = parcel.getWeight() * 2 + parcel.getDaysInDepot() * 0.5;
        Log.getInstance().addLog("Calculated Fee: " + fee + " for Parcel ID: " + parcel.getParcelID());
        return fee;
    }
}
