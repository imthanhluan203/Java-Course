package Section15.CollectionOverview;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] names = {"Anna","Bob","Carol","David","Edna"};
        Collection<String> list = new ArrayList<>(Arrays.asList(names));
        System.out.println(list);
        list.add("Fred");
        list.addAll(Arrays.asList("George","Gary","Grace"));
        System.out.println(list);
        System.out.printf("Gary in the list:%s%n",list.contains("Gary"));
        list.removeIf(s->s.charAt(0) == 'G');
        System.out.println(list);
        System.out.printf("Gary in the list:%s%n",list.contains("Gary"));
    }
}
