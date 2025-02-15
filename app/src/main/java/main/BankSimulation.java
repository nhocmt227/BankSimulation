package main;

import bank.Bank;
import bank.Counter;
import bank.Customer;
import bank.Queue;
import events.Arrival;
import events.Event;
import exceptions.AppException;
import utils.Converter;
import utils.Parser;

import java.util.ArrayList;
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
                System.out.println("");
                break;
            }
            try {
                Customer customer = Converter.createCustomer(input);
                this.initEvents.add(new Arrival(customer.getArrivalTime(), customer, this.bank));
                id += 1;
            } catch (AppException e) {
                System.out.println(e.getMessage());
            }
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
