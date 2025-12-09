package Section23.HttpProject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URIBasic {
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        URI uri = new URI("https://learnprogramming.academy");
        URI uri1 = new URI("courses/spring-framework-5-masterclass");
        uri = uri.resolve(uri1);
        printInfo(uri);
        printInfo(uri.toURL());
    }
    public static void printInfo(URI uri){
        System.out.printf("""
                ------------------------------------------------------
                scheme:[//authority][/path][?query][#fragment]
                ------------------------------------------------------
                scheme:%s
                authority:%s
                path:%s
                query:%s
                fragment:%s%n
                """,uri.getScheme(),uri.getAuthority(),
                uri.getPath(),uri.getQuery(),uri.getFragment());
    }
    public static void printInfo(URL uri){
        System.out.printf("""
                ------------------------------------------------------
                scheme:[//authority][/path][?query][#fragment]
                ------------------------------------------------------
                authority:%s
                path:%s
                query:%s
                """,uri.getAuthority(),
                uri.getPath(),uri.getQuery());
    }
}
