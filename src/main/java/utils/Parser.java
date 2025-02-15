package src.main.java.utils;

import src.main.java.bank.Customer;
import src.main.java.exceptions.AppException;
import src.main.java.exceptions.ParseException;

public class Parser {
    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidCustomerInput(String input) {
        // Define regex pattern for "1.0 1.0 1"
        String CUSTOMER_PATTERN = "^(-?\\d+(\\.\\d+)?\\s+){2}\\d+$";
        // Match input against regex
        return input.trim().matches(CUSTOMER_PATTERN);
    }

    public static String[] parse(String input) {
        return input.trim().split("\\s+");
    }
}
