package com.lostfound.model;

import java.time.LocalDate;

public class Item {

    private int itemId;
    private int userId;
    private String itemName = "";
    private String itemType = "Electronic";
    private String description = "";
    private String location = "";
    private String dateReported = "";      
    private String reportType = "Lost";    
    private String status = "Unclaimed";   

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
    public String getClaimDeadline() {

        if (reportType.equals("Found") && !dateReported.equals("")) {

            
            LocalDate reported = LocalDate.parse(dateReported);
            LocalDate deadline = reported.plusDays(14);

          
            String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

            int day = deadline.getDayOfMonth();
            int month = deadline.getMonthValue();
            int year = deadline.getYear();

            return day + " " + months[month - 1] + " " + year;
        }

        return "N/A";  
    }
}