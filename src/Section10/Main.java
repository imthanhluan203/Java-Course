package Section10;

import java.util.Comparator;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<LinkedListChallenge> place = new LinkedList<>();
        place.add(new LinkedListChallenge("Adelaide",1374));
        place.add(new LinkedListChallenge("Alice Springs",2771));
        place.add(new LinkedListChallenge("Brisbane",917));
        place.add(new LinkedListChallenge("Darwin",3972));
        place.add(new LinkedListChallenge("Melbourne",877));
        place.add(new LinkedListChallenge("Perth",3923));
        System.out.println(place);
        place.sort(new MyObjectComparator());
        System.out.println(place);
        var iterPlace = place.listIterator(0);
        LinkedListChallenge fistPlace = new LinkedListChallenge("Sydney", 0);
        while (iterPlace.hasNext()) {
            LinkedListChallenge current = iterPlace.next();
            System.out.println(fistPlace + "---->" + current);
        }
        System.out.println("---".repeat(20));
        while (iterPlace.hasPrevious()){
            System.out.println(iterPlace.previous() + "---->" + fistPlace);
        }
    }
}
class MyObjectComparator implements Comparator<LinkedListChallenge> {
    @Override
    public int compare(LinkedListChallenge o1, LinkedListChallenge o2) {
        int result = Integer.compare(o1.getDistance(), o2.getDistance());
        return result;
    }
}
