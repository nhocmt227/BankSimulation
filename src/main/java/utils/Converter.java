package src.main.java.utils;

import src.main.java.bank.Customer;
import src.main.java.exceptions.AppException;
import src.main.java.exceptions.ParseException;
import src.main.java.exceptions.TaskException;
import src.main.java.tasks.Deposit;
import src.main.java.tasks.Task;
import src.main.java.tasks.Withdrawal;

import static src.main.java.utils.Parser.isValidCustomerInput;
import static src.main.java.utils.Parser.parse;

public class Converter {

    public static Task taskIdToTask(int taskId) throws TaskException {
        return switch (taskId) {
            case 0 -> new Deposit();
            case 1 -> new Withdrawal();
            default -> throw new TaskException("Invalid Task ID");
        };
    }

    public static Customer createCustomer(String input) throws AppException {
        if (!isValidCustomerInput(input)) {
            throw new ParseException("ParseException: Invalid customer format");
        }
        String[] parts = parse(input);
        double arrivalTime = Double.parseDouble(parts[0]);
        double serviceTime = Double.parseDouble(parts[1]);
        int taskId = Integer.parseInt(parts[2]);
        if (arrivalTime < 0 || serviceTime < 0) {
            throw new ParseException("ParseException: Invalid customer format");
        }
        return new Customer(arrivalTime, serviceTime, Converter.taskIdToTask(taskId));
    }
}
