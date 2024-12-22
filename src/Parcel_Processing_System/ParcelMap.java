package Parcel_Processing_System;

import java.util.HashMap;
import java.util.Map;
public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    //Constructor
    public ParcelMap(){
        this.parcelMap = new HashMap<>();
    }

    //Add a parcel to the map
    public void addParcel (Parcel parcel){
        parcelMap.put(parcel.getParcelID(), parcel);
    }

    // To retrieve a parcel by ID
    public Parcel getParcel (String parcelID){
        return parcelMap.get(parcelID);
    }

    //To remove a parcel with its ID
    public Parcel removeParcel(String parcelID){
        return parcelMap.remove(parcelID);
    }

    //To display all parcel
    public void displayParcels (){
        for (Parcel parcel: parcelMap.values()){
            System.out.println (parcel);
        }
    }

}
