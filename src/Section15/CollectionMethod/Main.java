package Section15.CollectionMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Card> desks = Card.getStandardDeck();
        Card.printDesk(desks,"Current Deck",13);
        Card[] cardArray = new Card[13];
        Card cardHeart = Card.getFaceCard(Card.Suit.HEART,'A');
        Arrays.fill(cardArray,cardHeart);
        Card.printDesk(Arrays.asList(cardArray),"Ace of Heart",13);

        List<Card> cards = new ArrayList<>(52);
        Collections.fill(cards,Card.getFaceCard(Card.Suit.CLUB,'K'));
        Card.printDesk(cards,"Collection Deck",13);
        System.out.println(cards);

        List<Card> cCopy = Collections.nCopies(13,Card.getFaceCard(Card.Suit.CLUB,'K'));
        System.out.println(cCopy);
        System.out.println("*".repeat(30));
        Collections.addAll(cards,cardArray);
        Collections.addAll(cards,cardArray);
        System.out.println(cards);
        Collections.copy(cards,cCopy);
        System.out.println(cards);
        cards = List.copyOf(cCopy);
        System.out.println(cards);

        List<Card> deck = Card.getStandardDeck();
        Card.printDesk(deck,"Current Deck",13);
        Collections.shuffle(deck);
        Card.printDesk(deck,"Current Deck",13);
        Collections.reverse(deck);
        Card.printDesk(deck,"Current Deck",13);
        Collections.sort(deck,
                (o1,o2)->Integer.valueOf(o1.rank()).compareTo(Integer.valueOf(o2.rank())));
        Card.printDesk(deck,"Current Deck",13);
        Collections.reverse(deck);
        Card.printDesk(deck,"Current Deck",4);

        List<Card> kings = new ArrayList<>(deck.subList(4,8));
        Card.printDesk(kings,"Current King",4);
        List<Card> tens = new ArrayList<>(deck.subList(16,20));
        Card.printDesk(tens,"Current ten",4);



        int indexOfsublist =Collections.indexOfSubList(deck,tens);
        System.out.println("Index of ten:" + indexOfsublist);
        System.out.println("Contains:" + deck.containsAll(tens));

        Card.printDesk(deck,"Current Deck",13);
        Collections.shuffle(deck);
        Card.printDesk(deck,"Current Deck",13);
        Collections.sort(deck,
                (o1,o2)->Integer.valueOf(o1.rank()).compareTo(Integer.valueOf(o2.rank())));
        Card.printDesk(deck,"Current Deck",13);
        Card tenOfHearts = Card.getNumericCard(Card.Suit.HEART,10);
        int foundIndex = Collections.binarySearch(deck,tenOfHearts,(o1,o2)->Integer.valueOf(o1.rank()).compareTo(Integer.valueOf(o2.rank())));
        System.out.println(foundIndex);
        System.out.println(deck.get(foundIndex));

        Card tenOfClubs = Card.getNumericCard(Card.Suit.CLUB,10);
        Collections.replaceAll(deck,tenOfClubs,tenOfHearts);
        System.out.println(deck.subList(32,36));

    }
}
