package Section14.LamdaTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    record Person(String firstName, String lastname) {
        @Override
        public String toString() {
            return "[" + firstName + "  " + lastname + "]";
        }

        public static void main(String[] args) {
            List<Person> people = new ArrayList<>(Arrays.asList(
                    new Main.Person("Lucy", "Van Pelt"),
                    new Person("Sally", "Brown"),
                    new Person("Linus", "Van Pelt"),
                    new Person("Peppermint", "Patty"),
                    new Person("Charlie", "Brown")
            ));
            for(var ob : people){
                System.out.print(ob);
            }
            var comparator = new Comparator<Person>(){
                @Override
                public int compare(Person o1, Person o2) {
                    return o1.lastname.compareTo(o2.lastname);
                }
            };
            people.sort((o1, o2) -> o1.lastname.compareTo(o2.lastname));
            System.out.println();
            System.out.println("---".repeat(30));
            for(var ob : people){
                System.out.print(ob);
            }
            interface EnhancedComparator<T> extends Comparator<T>{
                int secondLevel(T o1, T o2);
            }
            var enhancedComparator = new EnhancedComparator<Person>(){

                @Override
                public int compare(Person o1, Person o2) {
                    int result = o1.lastname.compareTo(o2.lastname);
                    return result == 0 ? secondLevel(o1,o2) : result;
                }

                @Override
                public int secondLevel(Person o1, Person o2) {
                    return o1.firstName.compareTo(o2.firstName);
                }
            };
            people.sort(enhancedComparator);
            System.out.println();
            System.out.println("---".repeat(30));
            for(var ob : people){
                System.out.print(ob);
            }

        }
    }
}
