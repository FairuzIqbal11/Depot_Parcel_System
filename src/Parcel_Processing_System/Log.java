package Parcel_Processing_System;

public class Log {
    private static Log instance;
    private StringBuilder logData;

    //private constructor
    private Log(){
        logData = new StringBuilder();

    }

    //To get the single instance of log
    public static Log getInstance(){
        if (instance == null){
            instance= new Log();
        }
        return instance;
    }

    // To add a log entry
    public void addLog (String entry){
        logData.append(entry).append("\n");
    }

    // To display all logs
    public void displayLogs(){
        System.out.println(logData.toString());
    }

}
