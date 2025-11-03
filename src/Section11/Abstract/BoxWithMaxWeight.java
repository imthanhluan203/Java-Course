package Section11.Abstract;

import java.util.ArrayList;

public class BoxWithMaxWeight extends Box{
    private ArrayList<Item> arr;
    private int capacity;
    public BoxWithMaxWeight(int capacity){
        this.capacity = capacity;
        arr=new ArrayList<>();
    }
    @Override
    public void add(Item item){
        if(arr.size() < capacity){
            arr.add(item);
        }
    }
    @Override
    public boolean isInBox(Item item){

    }

}
