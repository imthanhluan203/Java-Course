package Section12.GenericsExtra.util;

import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends QueryItem> extends ArrayList<T> {
    public QueryList(List<T> student) {
        super(student);
    }

    // Constructor mặc định (nên thêm để linh hoạt)
    public QueryList() {
        super(); // hoặc để trống, vì mặc định vẫn gọi super()
    }

    @Override
    public boolean add(T t) {
        return super.add(t);
    }

    @Override
    public T remove(int index) {
        return super.remove(index);
    }
//    public List<T> getMatches(String field, String value){
//        List<T> matches = new ArrayList<>();
//        for(var item:items){
//            if(item.matchFieldValue(field,value)){
//                matches.add(item);
//            }
//        }
//        return matches;
//    }
//
//    public static <S extends QueryItem> List<S> getMatches(List<S> items,String field,String value){
//        List<S> matches = new ArrayList<>();
//        for(var item:items){
//            if(item.matchFieldValue(field,value)){
//                matches.add(item);
//            }
//        }
//        return matches;
 //   }
}
