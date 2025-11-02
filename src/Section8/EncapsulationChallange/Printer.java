package Section8.EncapsulationChallange;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted = 0;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        this.tonerLevel = (tonerLevel<=100 && tonerLevel >=-1) ? tonerLevel : -1;
        this.duplex = duplex;
    }
    public int addToner(int tonerAmount){
        if(tonerAmount >=0 && tonerAmount <= 100){
            tonerLevel = (tonerLevel + tonerAmount > 100) ? -1 : tonerLevel + tonerAmount;
            return tonerLevel;
        }
        return -1;
    }
}
