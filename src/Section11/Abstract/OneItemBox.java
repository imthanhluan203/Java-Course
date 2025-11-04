package Section11.Abstract;

import java.util.ArrayList;

public class OneItemBox extends Box{
    private int capacity;
    private ArrayList<Item> arr;
    public OneItemBox(){
        capacity = 1;
        arr = new ArrayList<>();
    }
    @Override
    public void add(Item item){
        if(capacity > 0){
            int amount = item.getAmount();;
            if(amount > capacity){
                amount = capacity;
            }
            System.out.println("Amount is:" + amount);
            arr.add(new Item(item.getName(),amount));
            this.capacity -= amount;
            System.out.println("Add Item successfully");
            return;
        }
        System.out.println("Excess capacity");
        return;

    }
    @Override
    public boolean isInBox(Item item){
        for(var it : arr){
            if(it.equals(item)){
                return true;
            }
        }
        return false;
    }
}
