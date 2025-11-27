package Leetcode150;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public static void main(String[] args) {
        String s="xx";
        String t="x";
        //System.out.println(isAnagram(s,t));
        isAnagram(s,t);
    }
    public static void isAnagram(String s, String t) {
        Map<Character,Integer> S1 = new HashMap<>();
        Map<Character,Integer> S2 = new HashMap<>();
        for(var ch : s.toCharArray()){
            if(S1.containsKey(ch)){
                int a = S1.get(ch);
                int result = a + 1;
                S1.put(ch,result);
            }else {
                S1.put(ch,1);
            }
        }
        for(var ch : t.toCharArray()){
            if(S2.containsKey(ch)){
                int a = S2.get(ch);
                int result = a + 1;
                S2.put(ch,result);
            }else {
                S2.put(ch,1);
            }
        }
        S1.forEach((k,v)->System.out.println(k +":" + v));
        S2.forEach((k,v)->System.out.println(k +":" + v));
        //return S1.equals(S2);
    }
}
