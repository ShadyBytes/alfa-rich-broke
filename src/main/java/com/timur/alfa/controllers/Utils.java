package com.timur.alfa.controllers;

public class Utils {
    public static String numberToXXFormatter(int monthOrDayIntValue) {
        if (monthOrDayIntValue < 10) {
            return "0" + monthOrDayIntValue;
        } else {
            return String.valueOf(monthOrDayIntValue);
        }
    }
}
