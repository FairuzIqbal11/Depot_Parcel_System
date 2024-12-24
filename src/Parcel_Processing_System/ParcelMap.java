package Parcel_Processing_System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    // Constructor
    public ParcelMap() {
        this.parcelMap = new HashMap<>();
    }

    // Add a parcel to the map
    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelID(), parcel);
    }

    // To retrieve a parcel by ID
    public Parcel getParcel(String parcelID) {
        return parcelMap.get(parcelID);
    }

    // To remove a parcel with its ID
    public Parcel removeParcel(String parcelID) {
        return parcelMap.remove(parcelID);
    }

    // To display all parcels
    public String getParcelDetails() {
        StringBuilder details = new StringBuilder("Parcels:\n");
        for (Parcel parcel : parcelMap.values()) {
            details.append(parcel.toString()).append("\n");
        }
        return details.toString();
    }

    // To get all parcels as a list
    public List<Parcel> getAllParcels() {
        return new ArrayList<>(parcelMap.values());
    }

    // To update an existing parcel in the map
    public void updateParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelID(), parcel); // Ensure updated parcel is stored
    }


    // Sorting parcels by customer surname
    public List<Parcel> getSortedParcelsByCustomerSurname(QueofCustomers customerQueue) {
        List<Parcel> sortedParcels = new ArrayList<>();

        // Map customer to their parcels
        for (Customer customer : customerQueue.getCustomerQueue()) {
            Parcel parcel = parcelMap.get(customer.getParcelID());
            if (parcel != null) {
                sortedParcels.add(parcel);
            }
        }

        // Sort parcels by customer's surname
        sortedParcels.sort((parcel1, parcel2) -> {
            String surname1 = getSurname(getCustomerNameByParcelId(customerQueue, parcel1.getParcelID()));
            String surname2 = getSurname(getCustomerNameByParcelId(customerQueue, parcel2.getParcelID()));
            return surname1.compareTo(surname2);
        });

        return sortedParcels;
    }

    // Helper method to extract surname from a full name
    private String getSurname(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return ""; // Handle null or empty names
        }
        String[] nameParts = fullName.split(" ");
        return nameParts[nameParts.length - 1]; // Surname is the last word
    }

    // Helper method to get the customer's name by parcel ID
    private String getCustomerNameByParcelId(QueofCustomers customerQueue, String parcelID) {
        for (Customer customer : customerQueue.getCustomerQueue()) {
            if (customer.getParcelID().equals(parcelID)) {
                return customer.getName();
            }
        }
        return ""; // Return empty if no customer is found
    }
}
