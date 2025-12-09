package Section23.HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WebContent {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.geeksforgeeks.org/java/introduction-java-servlets/");
        var urlConnect = url.openConnection();
        InputStream stream = urlConnect.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }
        System.out.println(urlConnect.getHeaderField("Content-Type"));
    }
}
