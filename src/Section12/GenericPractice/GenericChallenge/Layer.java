package Section12.GenericPractice.GenericChallenge;

import java.util.ArrayList;
import java.util.List;

public class Layer<T extends Mappable> {
    private List<T> layer = new ArrayList<>();
    public void add(T t){
        layer.add(t);
    }
    public void printAllLayer(){
        for(T t : layer){
            t.render();
        }
    }
}
