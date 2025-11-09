package Section15.SetAndMaps;


import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetMain {
    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        NavigableSet<Contact> sorted = new TreeSet<>(((o1, o2) -> o1.getName().compareTo(o2.getName())));
        sorted.addAll(phones);
        sorted.addAll(emails);
        printData("Tree set with phone",sorted);

        NavigableSet<String> justName = new TreeSet<>();
        phones.forEach(x->justName.add(x.getName()));
        emails.forEach(x->justName.add(x.getName()));
        System.out.println(justName);
    }
    public static void printData(String header, Collection<Contact> contact){
        System.out.println("-".repeat(50));
        System.out.println(header);
        System.out.println("-".repeat(50));
        contact.forEach(x->System.out.println(x));
    }
}
