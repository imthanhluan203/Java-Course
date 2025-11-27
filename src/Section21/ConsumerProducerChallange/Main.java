package Section21.ConsumerProducerChallange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ShoeWareHouse myWareHouse = new ShoeWareHouse();
        Random random = new Random(42);
        var multiExecutors = Executors.newFixedThreadPool(15);
        List<Callable<Order>> multilOrder = new ArrayList<>();
        for(int i=0;i<15;i++){
            int id = random.nextInt(10000);
            String type = ShoeWareHouse.productList[random.nextInt(0,4)];
            int quantityOrder = random.nextInt(1,1000);
            multilOrder.add(()->{
                return myWareHouse.receiveOrder(new Order(id,type,quantityOrder));
            });
        }
        for(int i=0;i<5;i++){
            multilOrder.add(()->{
                return myWareHouse.fullFillOrder();
            });
        }
        try{
            var results = multiExecutors.invokeAll(multilOrder);
            for(var result : results){
                System.out.println(result.get(500, TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            multiExecutors.shutdown();
        }
        System.out.println("Final order in Shop");
        myWareHouse.getOrders().forEach(System.out::println);
    }
}
