package events;

import bank.Bank;
import bank.Counter;
import bank.Queue;

public class BankStub extends Bank {
    private Counter[] counters;

    private Queue queue;

    public BankStub(Counter[] counters, Queue queue) {
        super(counters, queue);
    }

    public Counter getAvailableCounter() {
        for (Counter counter : counters) {
            if (counter.isAvailable()) {
                return counter;
            }
        }
        return null;
    }

    public Queue getQueue() {
        return this.queue;
    }
}
