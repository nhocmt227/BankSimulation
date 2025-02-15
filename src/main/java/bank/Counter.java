package src.main.java.bank;

public class Counter {

    private static int idCount = 0;
    private int id;
    private boolean availability;


    public Counter() {
        this.id = idCount;
        idCount += 1;
        this.availability = true;
    }

    public int getId() {
        return this.id;
    }

    public boolean isAvailable() {
        return this.availability;
    }

    public void serve() {
        this.availability = false;
    }

    public void unServe() {
        this.availability = true;
    }

    @Override
    public String toString() {
        return String.format("S%d", this.id);
    }

}
