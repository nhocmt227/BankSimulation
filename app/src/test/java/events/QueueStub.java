package events;

import bank.Queue;

public class QueueStub extends Queue {
    private boolean isFull;

    /**
     * Constructor for a queue.
     *
     * @param size The maximum num of elements we can put in the queue.
     */
    public QueueStub(int size) {
        super(size);
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    @Override
    public boolean isFull() {
        return this.isFull;
    }
}
