package com.heroes.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
    /**
     * read text file with attributes for an unit. creates a hash map where key is a property name and value
     * is property value
     *
     * @param path to the file as a String
     * @throws IOException
     * @returns hashMap object
     */
    public static HashMap fileRead(String path) throws IOException {
        HashMap<String, String> unitProporties = new HashMap<>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] propertyKeyAndValue = line.split(",", 2);
            if (propertyKeyAndValue.length >= 2) {
                String key = propertyKeyAndValue[0];
                String value = propertyKeyAndValue[1];
                unitProporties.put(key, value);
            } else {
                System.out.println("wrong unitProperty in txt file: " + line);
            }
        }
        reader.close();
        return unitProporties;
    }
}



