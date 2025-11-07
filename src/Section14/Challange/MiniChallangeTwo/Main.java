package Section14.Challange.MiniChallangeTwo;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] name = {"Luan","Dat","Ha","Bob","Anna"};
        List<String> new_name = Arrays.asList(name);
        Arrays.setAll(name,x-> name[x].toUpperCase());
        System.out.println("=".repeat(30));
        Arrays.asList(name).forEach(x->System.out.println(x));
        Arrays.setAll(name,x-> {
            return ".%s.".formatted(name[x]);
        });
        System.out.println("=".repeat(30));
        Arrays.asList(name).forEach(x->System.out.println(x));
    }
}
