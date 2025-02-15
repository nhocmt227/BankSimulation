package src.main.java.main;

import src.main.java.bank.Bank;
import src.main.java.bank.Counter;
import src.main.java.bank.Customer;
import src.main.java.events.Arrival;
import src.main.java.exceptions.AppException;
import src.main.java.events.Event;
import src.main.java.bank.Queue;
import src.main.java.utils.Converter;
import src.main.java.utils.Parser;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements a bank simulation.
 */
public class BankSimulation extends Simulation {
    /**
     * The availability of counters in the bank.
     */
    private Bank bank;
    /**
     * The list of customer arrival events to populate
     * the simulation with.
     */
    private final List<Event> initEvents = new ArrayList<>();

    private final Scanner sc;
    private int maxQueueLen;
    private int numOfCounters;


    /**
     * Constructor for a bank simulation.
     *
     * @param sc A scanner to read the parameters from.  The first
     *           integer scanned is the number of customers; followed
     *           by the number of service counters.  Next is a
     *           sequence of (arrival time, service time) pair, each
     *           pair represents a customer.
     */
    public BankSimulation(Scanner sc) {
        this.sc = sc;

        initParams();

        int id = 0;
        while (true) {
            System.out.print("Customer " + (id + 1) + " [arrivalTime, serviceTime, task ID]: ");
            String input = sc.nextLine();
            if (input.trim().equals("run")) {
                break;
            }
            try {
                Customer customer = Converter.createCustomer(input);
                System.out.println(customer);
                this.initEvents.add(new Arrival(customer.getArrivalTime(), customer, this.bank));
                id += 1;
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("InitEvents length: " + initEvents.size());
        }
    }

    /**
     * Retrieve an array of events to populate the
     * simulator with.
     *
     * @return An array of events for the simulator.
     */
    @Override
    public List<Event> getInitialEvents() {
        return new ArrayList<>(initEvents);
    }

    public void initParams() {
        while (true) {
            System.out.print("Number of counters: ");
            String input = sc.nextLine();
            if (!Parser.isValidInteger(input)) {
                continue;
            }
            this.numOfCounters = Integer.parseInt(input);
            if (this.numOfCounters > 0) {
                break;
            }
        }
        Counter[] counters = new Counter[numOfCounters];
        for (int i = 0; i < numOfCounters; i++) {
            counters[i] = new Counter();
        }

        while (true) {
            System.out.print("Max queue length: ");
            String input = sc.nextLine();
            if (!Parser.isValidInteger(input)) {
                continue;
            }
            this.maxQueueLen = Integer.parseInt(input);
            if (this.maxQueueLen > 0) {
                break;
            }
        }
        this.bank = new Bank(counters, new Queue(maxQueueLen));
    }
}
