package src.main.java.events;

import src.main.java.bank.Bank;
import src.main.java.bank.Counter;
import src.main.java.bank.Customer;

public class ServiceBegin extends Event {

    private Customer customer;
    private Counter counter;
    private Bank bank;

    public ServiceBegin(double time, Customer customer, Counter counter, Bank bank) {
        super(time);
        this.customer = customer;
        this.counter = counter;
        this.bank = bank;
    }

    @Override
    public Event[] simulate() {
        counter.serve();
        double endTime = this.getTime() + this.customer.getServiceTime();
        return new Event[]{new ServiceEnd(endTime, this.customer,
                this.counter, this.bank)};
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format(": %s %s begin (by %s)",
                this.customer.toString(), this.customer.getTask().toString(), this.counter.toString());
        return super.toString() + str;
    }
}
