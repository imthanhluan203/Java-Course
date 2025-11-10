package Section15.SetAndMaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
    public static void main(String[] args) {
        List<Contact> phones = ContactData.getData("phone");
        List<Contact> emails = ContactData.getData("email");
        List<Contact> fulllist = new ArrayList<>();
        fulllist.addAll(phones);
        fulllist.addAll(emails);
        fulllist.forEach(x->System.out.println(x));
        System.out.println("-".repeat(40));
        Map<String,Contact> contacts = new HashMap<>();
        fulllist.forEach(x->{
            Contact duplicate = contacts.putIfAbsent(x.getName(),x);
            if(duplicate!=null){
                contacts.put(x.getName(),duplicate.mergeContactData(x));
            }

        });
        contacts.forEach((k,v)->System.out.printf("key=%s , value=%s %n",k,v));

        contacts.clear();
        System.out.println("-".repeat(40));
        fulllist.forEach(x -> contacts.merge(x.getName(),x,
                (p,c)->{
                    return p.mergeContactData(c);
                }));
        contacts.forEach((k,v)->System.out.printf("key=%s , value=%s %n",k,v));
        for(String contactName : new String[]{"Daisy Duck","Daffy Duck","Scrooge McDuck"}){
            contacts.computeIfAbsent(contactName,(k)->new Contact(k));
        }
        System.out.println("-".repeat(40));

        contacts.forEach((k,v)->System.out.printf("key=%s , value=%s %n",k,v));
        System.out.println("-".repeat(40));
        Contact success = contacts.remove("Daffy Duck");
        System.out.println("-".repeat(40));
        System.out.println(success);
        contacts.forEach((k,v)->System.out.printf("key=%s , value=%s %n",k,v));



    }
}
