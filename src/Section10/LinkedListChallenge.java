package Section10;

public class LinkedListChallenge {
    private String town;
    private int distance;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LinkedListChallenge(String town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "{" +
                "town='" + town + '\'' +
                ", distance=" + distance +
                '}';
    }
}
