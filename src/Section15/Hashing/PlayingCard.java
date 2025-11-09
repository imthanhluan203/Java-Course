package Section15.Hashing;

public class PlayingCard {
    private String suit;
    private String face;
    private int internalHash;

    public PlayingCard(String suit, String face) {
        this.suit = suit;
        this.face = face;
        internalHash = suit.equalsIgnoreCase("Hearts")?11:12;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Card is checking equality");
        return true;
    }

    @Override
    public int hashCode() {
        return internalHash;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

}
