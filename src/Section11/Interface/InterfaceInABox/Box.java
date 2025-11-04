package Section11.Interface.InterfaceInABox;

import java.util.ArrayList;

public class Box implements Packable{
    private ArrayList<Packable> container;
    private double capacity;
    private double remain_capacity;
    private double box_weight;
    public Box(double capacity){
        this.remain_capacity = capacity;
        this.capacity = capacity;
        container = new ArrayList<>();
        box_weight = 0;
    }
    public void add(Packable item){
        double weight = item.weight();
        if(weight>capacity){
            System.out.println(item+" excess capacity!!!");
            return;
        }
        capacity -= weight;
        container.add(item);
        System.out.println(item+" add successfully");
        box_weight += weight;
        return;
    }
    @Override
    public double weight() {
        return box_weight;
    }

    @Override
    public String toString() {
        return String.format("Box: %d items, total weight %.2f kg",container.size(),weight());
    }
}
