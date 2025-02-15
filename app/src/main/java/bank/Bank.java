package bank;

public class Bank {

    private Counter[] counters;

    private Queue queue;

    public Bank(Counter[] counters, Queue queue) {
        this.counters = counters;
        this.queue = queue;
    }

    public Counter getAvailableCounter() {
        for (Counter counter : counters) {
            if (counter.isAvailable()) {
                return counter;
            }
        }
        return null;
    }

    public int getSize() {
        return this.counters.length;
    }

    public Queue getQueue() {
        return this.queue;
    }

}
