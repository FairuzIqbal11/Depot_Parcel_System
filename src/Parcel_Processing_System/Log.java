package Parcel_Processing_System;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    // Retrieve log data
    public String getLogData() {
        return logData.toString();
    }
    public void writeLogToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(logData.toString());
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }


}
