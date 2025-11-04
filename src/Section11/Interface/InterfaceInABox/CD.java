package Section11.Interface.InterfaceInABox;

public class CD implements Packable {
    private String artist;
    private String nameCD;
    private int pubYear;
    public static double weight = 0.1;
    public CD(String artist, String nameCD, int pubYear) {
        this.artist = artist;
        this.nameCD = nameCD;
        this.pubYear = pubYear;
    }

    @Override
    public double weight() {
        return CD.weight;
    }

    @Override
    public String toString() {
        return artist + ":" + nameCD + "(" + pubYear + ")";
    }
}
