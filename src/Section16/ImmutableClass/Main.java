package Section16.ImmutableClass;

public class Main {
    public static void main(String[] args) {
        Person jane = new Person("Jane","01/01/1930");
        Person jim = new Person("Jim","02/02/1932");
        Person joe = new Person("Joe","03/03/1934");

        Person[] johnsKids = {jane, jim, joe};
        Person john = new Person("Jonn","05/05/1900",johnsKids);
        System.out.println(john);

        Person[] kids = john.getKids();
        kids = null;
        john.setKids(kids);
        System.out.println(john);
    }
}
