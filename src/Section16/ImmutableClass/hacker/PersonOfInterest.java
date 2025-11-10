package Section16.ImmutableClass.hacker;

import Section16.ImmutableClass.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {
    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }

//    @Override
//    public PersonImmutable[] getKids() {
//        return super.kids;
//    }
}
