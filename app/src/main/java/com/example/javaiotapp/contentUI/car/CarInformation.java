package com.example.javaiotapp.contentUI.car;

public class CarInformation {
    private String brand;
    private String beginDate;
    private String description;
    private String endDate;

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }
    public String getEndDate() {
        return endDate;
    }

    public CarInformation(String brand, String beginDate, String description, String endDate) {
        this.brand = brand;
        this.beginDate = beginDate;
        this.description = description;
        this.endDate = endDate;
    }

    public CarInformation() {
    }
}

