package Section10;

public class Bank {
    private String name;
    private AutoBoxingChallenge customer;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customer=" + customer +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public AutoBoxingChallenge getCustomer() {
        return customer;
    }

    public void setCustomer(AutoBoxingChallenge customer) {
        this.customer = customer;
    }

    public Bank(String name, AutoBoxingChallenge customer) {
        this.name = name;
        this.customer = customer;
    }
}
