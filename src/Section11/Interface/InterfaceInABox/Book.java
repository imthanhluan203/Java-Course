package Section11.Interface.InterfaceInABox;
public class Book implements Packable{
    private String author;
    private String nameBook;
    private double weight;

    public Book(String author, String nameBook, double weight) {
        this.author = author;
        this.nameBook = nameBook;
        this.weight = weight;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return  author + ":" +nameBook;
    }
}
