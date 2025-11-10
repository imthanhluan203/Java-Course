package Section15.SetAndMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapViewMain {
    public static void main(String[] args) {
        Map<String,Contact> contacts = new HashMap<>();
        ContactData.getData("phone").forEach(c->contacts.put(c.getName(),c));
        ContactData.getData("email").forEach(c->contacts.put(c.getName(),c));
        Set<String> keysView = contacts.keySet();
        System.out.println("=".repeat(30));
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(contacts.get(k)));
        System.out.println("=".repeat(30));
        keysView.remove("Lucy Van Pelt");
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(contacts.get(k)));
        contacts.clear();
        ContactData.getData("phone").forEach(c->contacts.put(c.getName(),c));
        ContactData.getData("email").forEach(c->contacts.put(c.getName(),c));
        System.out.println("=".repeat(30));
        System.out.println(keysView);
        contacts.forEach((k,v) -> System.out.println(contacts.get(k)));
    }
}
