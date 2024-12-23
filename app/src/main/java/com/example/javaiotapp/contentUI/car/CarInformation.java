package com.example.javaiotapp.contentUI.car;

import androidx.annotation.NonNull;

public class CarInformation {
    private String brand;
    private String beginDate;
    private String LicensePlate;
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

    public void setLicensePlate(String LicensePlate) {
        this.LicensePlate = LicensePlate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }
    public String getEndDate() {
        return endDate;
    }

    public CarInformation(String brand, String beginDate, String LicensePlate, String endDate) {
        this.brand = brand;
        this.beginDate = beginDate;
        this.LicensePlate = LicensePlate;
        this.endDate = endDate;
    }

    public CarInformation() {
    }

    @NonNull
    @Override
    public String toString() {
        return "CarInformation{" +
                "brand='" + brand + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", LicensePlate='" + LicensePlate + '\'' +
                ", endDate='" + endDate + '\'';
    }
}

