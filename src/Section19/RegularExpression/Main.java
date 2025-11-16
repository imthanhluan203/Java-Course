package Section19.RegularExpression;

public class Main {
    public static void main(String[] args) {
        String helloWorld = "%s %s".formatted("Hello","World");
        String helloWord2 = String.format("%s %s", "Hello", "World");
        System.out.println("Using string's formatted method: " + helloWorld);
        System.out.println("Using string.format: " + helloWord2);
        String helloWorld3 = format("asdasd %s %s","Hello", "World");
        System.out.println("My own function: " + helloWorld3);

        String testString = "Anyone can learn abccccc's, 123's and any regular expression";
        String replace = "(-)";
        String[] patterns = {
                "abc*",
                "[0-9]",
                "[A-Z]"
        };
        System.out.println(testString);
        for(var pattern : patterns){
            String output = testString.replaceFirst(pattern,"(-)");
            System.out.println("Pattern: " + pattern + "=> " + output);
        }
    }
    private static String format(String regexp, String... args){
        int index = 0;
        while (regexp.matches(".*%s.*")){
            regexp = regexp.replaceFirst("%s",args[index++]);
        }
        return regexp;
    }
}
