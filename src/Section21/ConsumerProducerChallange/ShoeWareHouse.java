package Section21.ConsumerProducerChallange;

import java.util.ArrayList;
import java.util.List;

record Order(int id,String type,int quanOrder){}
public class ShoeWareHouse {
    public static String[] productList = {"Nike","Adidas","ThuongDinh","Asia"};
    private List<Order>orders;
    private final int MaxCapacity = 10;
    public ShoeWareHouse(){
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public ShoeWareHouse(List<Order> orders) {
        this.orders = orders;

    }
    public synchronized Order receiveOrder(Order o){
        while (this.orders.size() > MaxCapacity){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.orders.add(o);
        System.out.println("Order is received");
        notifyAll();
        return o;
    }
    public synchronized Order fullFillOrder(){
        while (this.orders.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        var ob = this.orders.remove(0);
        System.out.println(ob + " is removed");
        notifyAll();
        return ob;
    }
}
