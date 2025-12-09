package Section23.HttpProject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpExamplePost {
    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://example.com/");
        URL url = new URL("http://localhost:8080");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setDoOutput(true);
        String data = "firstname=Luan&lastname=Nguyen";
        int length = data.getBytes().length;
        httpURLConnection.setRequestProperty("Content-Length",String.valueOf(length));
        DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();

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
