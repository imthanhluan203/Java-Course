package Section11.Interface.TacoBoxes;

public class TripleTacoBox implements TacoBox {
    private int tacos;
    public TripleTacoBox(){
        tacos = 3;
    }
    @Override
    public int tacosRemaining() {
        return 0;
    }

    @Override
    public void eat() {

    }
}
