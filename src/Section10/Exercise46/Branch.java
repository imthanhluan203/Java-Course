package Section10.Exercise46;

import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;
    public Branch(String name){
        this.name = name;
        customers = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }
    public boolean newCustomer(String name,double value){
        for(int i = 0 ; i<customers.size();i++){
            Customer current = customers.get(i);
            if(current.getName() == name){
                return false;
            }
        }
        Customer new_cus = new Customer(name);
        new_cus.addTransaction(value);
        customers.add(new_cus);
        return true;
    }
    public boolean addCustomerTransaction(String name,double value){
        for(int i = 0 ; i<customers.size();i++){
            Customer current = customers.get(i);
            if(current.getName() == name){
                current.addTransaction(value);
                return true;
            }
        }
        return false;
    }
    public Customer findCustomer(String name){
        for(int i = 0 ; i<customers.size();i++){
            Customer current = customers.get(i);
            if(current.getName() == name){
                return current;
            }
        }
        return null;
    }
    public void print(){
        for(var ob : customers){
            System.out.println(ob);
        }
    }
}
