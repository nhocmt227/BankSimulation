package src.main.java.main;

import src.main.java.bank.Bank;
import src.main.java.bank.Counter;
import src.main.java.bank.Customer;
import src.main.java.events.Arrival;
import src.main.java.tasks.Deposit;
import src.main.java.tasks.Task;
import src.main.java.tasks.Withdrawal;
import src.main.java.events.Event;
import src.main.java.bank.Queue;

import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
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
  private Event[] initEvents;

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
    this.initEvents = new Event[100];

    System.out.println("Number of counters: ");
    this.numOfCounters = sc.nextInt();
    Counter[] counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter();
    }
    System.out.println("Counter length: " + counters.length);

    System.out.println("Max queue length: ");
    this.maxQueueLen= sc.nextInt();
    this.bank = new Bank(counters, new Queue(maxQueueLen));

    int id = 0;
    while (true) {
      // Check if the next input is "start"
      if (sc.hasNext("start")) {
        sc.next(); // Consume the "start" input
        break;     // Exit the loop
      }

      System.out.println("Customer " + (id + 1) + " [arrivalTime, serviceTime, task ID]: ");
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      int taskId = sc.nextInt();
      Task task = null;
      if (taskId == 0) {
        // src.main.java.tasks.Deposit
        task = new Deposit();
      } else if (taskId == 1) {
        // src.main.java.tasks.Withdrawal
        task = new Withdrawal();
      }
      Customer customer = new Customer(arrivalTime, serviceTime, task);
      System.out.println(customer.toString());
      initEvents[id] = new Arrival(arrivalTime, customer, this.bank);
      id += 1;
    }


  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }

}
