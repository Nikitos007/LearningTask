package ua.com.utils;

import java.sql.Date;

public class ParamUtils {

    public static Long StringToLong(String value) {
        try {
            return Long.valueOf(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer StringToInteger(String emplolyeeSalary) {
        try {
            return Integer.valueOf(emplolyeeSalary.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Date verifyDate(String employeeHireDateStr) {
        try {
            return Date.valueOf(employeeHireDateStr.trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String verifyString(String value) {
        if (value.trim().length() > 0) {
            return value;
        }
        return null;
    }
}
