package src.main.java.events;

import src.main.java.bank.Counter;
import src.main.java.bank.Bank;
import src.main.java.bank.Customer;
import src.main.java.bank.Queue;

public class Arrival extends Event {

    private Customer customer;

    private Bank bank;


    public Arrival(double time, Customer customer, Bank bank) {
        super(time);
        this.customer = customer;
        this.bank = bank;
    }

    @Override
    public Event[] simulate() {
        Counter counterAvailable = bank.getAvailableCounter();
        if (counterAvailable == null) {
            // No counter available, enqueue
            Queue q = this.bank.getQueue();
            if (q.isFull()) {
                // Queue is full, customr depart
                return new Event[]{new Departure(this.getTime(), this.customer, this.bank)};
            } else {
                // Queue not full, enqueue
                return new Event[]{new Enqueue(this.getTime(), this.customer, this.bank)};
            }

        } else {
            // Customer enter a counter
            return new Event[]{new ServiceBegin(this.getTime(), this.customer,
                    counterAvailable, this.bank)};
        }
    }


    @Override
    public String toString() {
        String str = "";
        str = String.format(": %s arrived %s", this.customer.toString(),
                this.bank.getQueue().toString());
        return super.toString() + str;
    }
}
