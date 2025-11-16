package Section19.Challange;

public class MainChallengeOne {
    public static void main(String[] args) {
        String regex = ".*[Hello, World!].*";
        String one = "Hello, World! asda asdas das das d aff gs r";
        String two ="asdasdasd dfasdf asdwsda Hello, World!";
        String three = " asdasada asdasd Hello, World! Hello, World! asdasdasda";
        if(one.matches(regex) && two.matches(regex) && three.matches(regex)){
            System.out.println("Correct answer");
        }
        }
}
