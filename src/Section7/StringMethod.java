package Section7;

public class StringMethod {
    public static void main(String[] args) {
        String s1 =new String("hello");
        String s2 =new String("hello");
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));
    }
}
