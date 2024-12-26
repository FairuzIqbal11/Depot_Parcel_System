package Parcel_Processing_System;

class LogObserver implements Manager.Observer {
    @Override
    public void update() {
        System.out.println("LogObserver: System updated.");
    }
}

