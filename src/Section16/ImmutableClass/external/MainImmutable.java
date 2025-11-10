package Section16.ImmutableClass.external;

import Section16.ImmutableClass.PersonImmutable;
import Section16.ImmutableClass.hacker.PersonOfInterest;

public class MainImmutable {
    public static void main(String[] args) {
        PersonImmutable jane = new PersonImmutable("Jane","01/01/1930");
        PersonImmutable jim = new PersonImmutable("Jim","02/02/1932");
        PersonImmutable joe = new PersonImmutable("Joe","03/03/1934");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("Jonn","05/05/1900",johnsKids);
        System.out.println(john);


        PersonImmutable[] kids = john.getKids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Thong","15/02/2003");
        System.out.println(john);
        johnsKids[0] = new PersonImmutable("Luan","13/01/2003");
        System.out.println(john);

        LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
        System.out.println(johnLiving);
        LivingPerson anne = new LivingPerson("Ann", null);
        johnLiving.addKid(anne);
        System.out.println(johnLiving);

        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);
        kids = johnCopy.getKids();
        System.out.println(kids[1]);
        kids[1] = anne;
        System.out.println(johnCopy);
        System.out.println(john);

    }
}
