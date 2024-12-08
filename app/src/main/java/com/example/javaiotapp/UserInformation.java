package com.example.javaiotapp;

public class UserInformation {
    private  String name;
    private  String date_of_birth;
    private  String address;
    private  String phone_number;

    public String getAddress() {
        return address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
