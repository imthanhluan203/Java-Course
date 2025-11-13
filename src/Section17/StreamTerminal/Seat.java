package Section17.StreamTerminal;

import java.util.Random;

public record Seat(char rowMarker, int seatNumber, boolean isRevered) {
    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker,seatNumber,new Random().nextBoolean());
    }
}
