package Section15.Hashing;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l","He","lo");
        String dText = "He".concat("llo");
        String eText = "hello";
        List<String> hellos = Arrays.asList(aText,bText,cText,dText,eText);
        hellos.forEach(s->System.out.println(s + ":"+s.hashCode()));
        Set<String>mySet = new HashSet<>(hellos);
        System.out.println("mySet = " + mySet);
        System.out.println("# of elements = "+mySet.size());

        PlayingCard aceHeards = new PlayingCard("Hearts","Ace");
        PlayingCard kingClubs = new PlayingCard("Clubs","King");
        PlayingCard queenSpades = new PlayingCard("Spades","Queen");
        List<PlayingCard> cards = new ArrayList<>(Arrays.asList(aceHeards,kingClubs,queenSpades));
        cards.forEach(s->System.out.println(s + " : "+ s.hashCode()));

        Set<PlayingCard> deck = new HashSet<>();
        for(var ob : cards){
            if(!deck.add(ob)){
                System.out.println("Find a duplicate");
            }
        }
        System.out.println("=".repeat(100));
        deck.forEach(s->System.out.println(s));
    }
}
