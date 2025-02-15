package bank;

import tasks.Task;

public class Customer {

    private static int idCount = 0;
    private int id;
    private double arrivalTime;
    private double serviceTime;
    private Task task;

    public Customer(double arrivalTime, double serviceTime, Task task) {
        this.id = idCount;
        idCount += 1;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.task = task;
    }

    public int getId() {
        return this.id;
    }

    public static int getIdCount() {
        return idCount;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime;
    }

    public Task getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("C%d", this.id);
    }

}
