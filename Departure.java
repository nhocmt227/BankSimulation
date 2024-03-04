public class Departure extends Event {
  
  private Customer customer;
  private Bank bank;

  public Departure(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override 
  public Event[] simulate() {
    return new Event[] {};
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s departed", this.customer.toString());
    return super.toString() + str;
  }
}
