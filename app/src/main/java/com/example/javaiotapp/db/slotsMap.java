package com.example.javaiotapp.db;

import java.util.HashMap;

public class slotsMap {
    private final HashMap<String, String> myMap;

    public slotsMap() {
        myMap = new HashMap<String, String>();
        myMap.put("A1", "full");
        myMap.put("A2", "empty");
        myMap.put("A3", "full");
        myMap.put("A4", "empty");
        myMap.put("A5", "full");
        myMap.put("A6", "full");
        myMap.put("A7", "full");
        myMap.put("A8", "empty");

        myMap.put("B1", "empty");
        myMap.put("B2", "full");
        myMap.put("B3", "empty");
        myMap.put("B4", "full");
        myMap.put("B5", "empty");
        myMap.put("B6", "empty");
        myMap.put("B7", "empty");
        myMap.put("B8", "full");
    }

    public HashMap<String, String> getMap() {
        return myMap;
    }
}
