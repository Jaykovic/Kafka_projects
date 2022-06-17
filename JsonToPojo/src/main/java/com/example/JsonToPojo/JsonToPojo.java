package com.example.JsonToPojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonToPojo {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            student student = mapper.readValue(new File("data/simple.json"), student.class);
            System.out.println("Student id: " + student.getId());
            System.out.println("First Name: " + student.getFirstName());
            System.out.println("Last Name: " + student.getLastName());
            System.out.println("Department: " + student.getDepartment());
            System.out.println("Active: " + student.isActive());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
