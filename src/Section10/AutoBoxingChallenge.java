package Section10;

import java.util.ArrayList;

public class AutoBoxingChallenge {
    private String name;
    private ArrayList<Double> transaction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Double> transaction) {
        this.transaction = transaction;
    }
    public void add_transaction(double value){
        transaction.add(value);
    }
    public AutoBoxingChallenge(String name, ArrayList<Double> transaction) {
        this.name = name;
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
