package Section10.Exercise46;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Double>transactions = new ArrayList<>();
    public Customer(String name){
        this.name = name;
        transactions = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public ArrayList<Double> transactions(){
        return transactions;
    }
    public void addTransaction(double value){
        transactions.add(value);
    }
    @Override
    public String toString(){
        return "Customer: " + name;
    }
}
