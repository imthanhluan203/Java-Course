package Section16.FinalExplored;

import java.util.*;

public class MainMailer {
    public static void main(String[] args) {
        String[] names = {"Ann Jones", "Ann Jones Ph.D", "Bob Jones M.D",
                "Carol Jones","Ed Green Ph.D","Ed Green M.D","Ed Black"};
        var population = getNames(names);
        population.forEach(x->System.out.println(x));
        Map<StringBuilder,Integer> counts = new TreeMap<>();
        population.forEach(x->{
            counts.merge(x,1, Integer::sum);
        });
        System.out.println(counts);
        var cleanedNames = standardizeNames(population);
        System.out.println(cleanedNames);
        System.out.println(counts);
    }
    private static List<StringBuilder> getNames(String[] name){
        List<StringBuilder> list = new ArrayList<>();
        int index = 3;
        Arrays.asList(name).forEach(x->{
            for(int i=0;i<index;i++){
                list.add(new StringBuilder(x));
            }
        });
        return list;
    }
    private static List<StringBuilder> standardizeNames(List<StringBuilder> data){
        List<StringBuilder> newList = new ArrayList<>();
        for(String ob : new String[] {"Ph.D","M.D",}){
            data.forEach(x -> {
                int indexStart = -1;
                if((indexStart = x.indexOf(ob)) > -1){
                    x.replace(indexStart - 1,indexStart + ob.length(),"" );
                }
                newList.add(x);
            });
        }
        return newList;
    }
}
