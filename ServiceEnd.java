public class ServiceEnd extends Event {

  private Customer customer;
  private Counter counter;
  private Bank bank;

  public ServiceEnd(double time, Customer customer, Counter counter, Bank bank) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
  }

  @Override 
  public Event[] simulate() {
    this.counter.unServe();
    Queue q = this.bank.getQueue();
    if (q.isEmpty()) {
      return new Event[] {new Departure(this.getTime(), this.customer, this.bank)};
    } else {
      // The queue is not empty, dequeue and customer depart
      Customer c = (Customer) q.deq();
      return new Event[] {new Departure(this.getTime(), this.customer, this.bank),
                          new ServiceBegin(this.getTime(), c, this.counter, this.bank)};
    }
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s %s done (by %s)",
        this.customer.toString(), this.customer.getTask().toString(), this.counter.toString());
    return super.toString() + str;
  }
}
