package Section19.PatternMatching;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String cardNumber = "10 divided by 2";
        Pattern pattern = Pattern.compile("(\\d) divided by (\\d)");
        Matcher matcher = pattern.matcher(cardNumber);

        String found = matcher.replaceAll("$1/$2");
        System.out.println("found: " + found);
    }
}
