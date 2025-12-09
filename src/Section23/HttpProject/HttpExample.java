package Section23.HttpProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExample {
    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://example.com/");
        URL url = new URL("http://localhost:8080");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("RESPONSE CODE:" + responseCode);
        if(responseCode != HTTP_OK){
            System.out.println("Can not connect to server");
            return;
        }
        printContent(httpURLConnection.getInputStream());
    }
    public static void printContent(InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }}
}
