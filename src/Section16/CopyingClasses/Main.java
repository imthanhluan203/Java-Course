package Section16.CopyingClasses;

import java.util.Arrays;

record Person(String name,String dob, Person[]kids){
    public Person(Person p) {
        this(p.name,p.dob,p.kids == null ? null : Arrays.copyOf(p.kids,p.kids.length));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}
public class Main {
    public static void main(String[] args) {
        Person joe = new Person("Joe","01/01/1961",null);
        Person jim = new Person("Jim","02/02/1961",null);
        Person jack = new Person("Jack","03/03/1963",new Person[]{joe,jim});
        Person jane = new Person("Jane","04/04/1964",null);
        Person jill = new Person("Jill","05/05/1965",new Person[]{joe,jim});

        Person[] person = new Person[]{joe,jim,jack,jane,jill};
        //Person[] personCopy = Arrays.copyOf(person,person.length);
        Person[] personCopy = new Person[5];
        Arrays.setAll(personCopy,i->new Person(person[i]));
        var jillKids = personCopy[4].kids();
        jillKids[1] = jane;
        for(int i=0;i<person.length;i++){
            if(person[i] == personCopy[i]){
                System.out.println("Equal References "+person[i]);
            }
            System.out.println(person[i]);
            System.out.println(personCopy[i]);
        }
    }
}
