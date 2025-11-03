package Section11.Abstract;

import java.util.ArrayList;

public abstract class Box {

    public abstract void add(Item item);

    public void add(ArrayList<Item> items) {
        for (Item item : items) {
            this.add(item);
        }
    }

    public abstract boolean isInBox(Item item);
}