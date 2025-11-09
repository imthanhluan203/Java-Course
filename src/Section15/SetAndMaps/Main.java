package Section15.SetAndMaps;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Contact> emails = ContactData.getData("email");
        List<Contact> phones = ContactData.getData("phone");
        printData("Phone List",phones);
        printData("Email List",emails);

        Set<Contact> emailContacts = new HashSet<>(emails);
        Set<Contact> phoneContacts = new HashSet<>(phones);
        printData("Phone List",phoneContacts);
        printData("Email List",emailContacts);

        int index = emails.indexOf(new Contact("Robin Hood"));
        Contact robinHood = emails.get(index);
        robinHood.addEmail("Sherwood Forest");
        printData("Phone List",phoneContacts);
        printData("Email List",emailContacts);
        robinHood.replaceOldEmail("RHood@sherwoodforest.com","RHood@sherwoodforest.org");
        printData("Phone List",phoneContacts);
        printData("Email List",emailContacts);

        Set<Contact> unionSet = new HashSet<>();
        unionSet.addAll(emailContacts);
        unionSet.addAll(phoneContacts);
        printData("A union B",unionSet);


    }
    public static void printData(String header,Collection<Contact> contact){
        System.out.println("-".repeat(50));
        System.out.println(header);
        System.out.println("-".repeat(50));
        contact.forEach(x->System.out.println(x));
    }
}
