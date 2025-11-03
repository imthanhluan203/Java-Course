package Section10.Exercise46;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches;
    public Bank(String name){
        this.name = name;
        branches = new ArrayList<>();
    }
    public boolean addBranch(String name){
        for(var t : branches){
            if(t.getName() == name){
                return false;
            }
        }
        branches.add(new Branch(name));
        return true;
    }
    public boolean addCustomer(String nameBranch,String nameCustomer,double transaction){
        boolean value = false;
        for(var ob : branches){
            if(ob.getName() == nameBranch){
                value = ob.newCustomer(nameCustomer,transaction);
            }
        }
        return value;
    }
    public boolean addCustomerTransaction(String nameBranch,String nameCustomer,double transaction){
        boolean value = false;
        for(var ob : branches){
            if(ob.getName() == nameBranch){
                value = ob.addCustomerTransaction(nameCustomer,transaction);
            }
        }
        return value;
    }
    public Branch findBranch(String nameBranch){
        for(var ob : branches){
            if(ob.getName() == nameBranch){
                return ob;
            }
        }
        return null;
    }
    public boolean listCustomers(String nameBranch,boolean print){
        if(print){
            Branch current = findBranch(nameBranch);
            if(current!=null){
                current.print();
                return true;
            }
        }
        return false;
    }
}
