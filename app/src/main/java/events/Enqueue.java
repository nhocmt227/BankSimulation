package events;

import bank.Bank;
import bank.Customer;
import bank.Queue;


public class Enqueue extends Event {

    private Customer customer;

    private Bank bank;

    private double time;

    public Enqueue(double time, Customer customer, Bank bank) {
        super(time);
        this.customer = customer;
        this.bank = bank;
    }

    @Override
    public Event[] simulate() {
        Queue q = this.bank.getQueue();
        q.enq(this.customer);
        return new Event[]{};
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": %s joined queue %s",
                this.customer.toString(),
                this.bank.getQueue().toString());
        return super.toString() + str;
    }
}
