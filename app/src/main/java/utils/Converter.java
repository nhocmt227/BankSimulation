package utils;

import bank.Customer;
import exceptions.AppException;
import exceptions.ParseException;
import exceptions.TaskException;
import tasks.Deposit;
import tasks.Task;
import tasks.Withdrawal;

import static utils.Parser.isValidCustomerInput;
import static utils.Parser.parse;

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
