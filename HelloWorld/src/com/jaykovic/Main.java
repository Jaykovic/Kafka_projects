package com.jaykovic;

import java.awt.*;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
       // primitive types
        //long viewsCount = 3_123_123_456L;
        //float score = 19.09F;

        // reference types
       // Date now = new Date();
        Point score1 = new Point(1,1);
        Point score2 = score1;
        score1.y = 3;

        String message = "Hello Jaykovic";

        String location = "C:\\windows\\kafka\\kafka-logs";

        // Onedimensional array
        int [] grades = {54, 40, 39, 74, 80};
        Arrays.sort(grades);
        System.out.println(Arrays.toString(grades));

        // Two dimensional array
        int [][] marks = {{60, 54, 59 }, {24, 37, 58}};

        System.out.println(Arrays.deepToString(marks));

        // Casting
            // Implicit casting
        String num = "1";
        int y = Integer.parseInt(num) + 5;

        System.out.println(y);

        // The math class
        //int result = Math.round(2.6F);
        //int result = (int)Math.ceil(2.6F);
        //int result = (int)Math.floor(2.6F);
        //double result = Math.random()* 100;
       // System.out.println(result);

        // Formatting numbers
        //String records = NumberFormat.getCurrencyInstance().format(12345678.999);
        //String records = NumberFormat.getPercentInstance().format(0.9);
       // System.out.println(records);

        // Reading inputs
       // Scanner scanner = new Scanner(System.in);
        //System.out.print("Age: ");
        //int age = scanner.nextInt();
        //System.out.print("Name: ");
       // String name = scanner.nextLine();
        //System.out.println("I am " + age + " years old");
       // System.out.println("My name is " + name);

        // Mortgage Calculator
        final byte Month_in_a_year = 12;
        final byte Percent = 100;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int Principal = scanner.nextInt();

        System.out.print("Annual Interest rate: ");
        float annualInterest = scanner.nextFloat();
        float monthlyInterest = annualInterest / Percent / Month_in_a_year;

        System.out.print("Period(Years): ");
        byte Years = scanner.nextByte();
        int numberOfPayments = Years * Month_in_a_year;

        double mortgage = Principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)/ Math.pow(1 + monthlyInterest, numberOfPayments) -1);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("Mortage: " + mortgageFormatted);

    }

}
