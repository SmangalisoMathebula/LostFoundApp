package com.lostfound.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class LogHelper {

   
    public static void writeLog(String userEmail, String action, String itemName) {
        try {
           
            String filePath = System.getProperty("user.home") + "/lostfound_log.txt";

          
            FileWriter fw = new FileWriter(filePath, true);
            PrintWriter pw = new PrintWriter(fw);

            Date now = new Date();

            pw.println(now + " | User: " + userEmail + " | Action: " + action + " | Item: " + itemName);

            pw.close();

        } catch (Exception e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }
}