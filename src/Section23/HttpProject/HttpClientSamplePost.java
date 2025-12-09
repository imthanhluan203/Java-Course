package Section23.HttpProject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpClientSamplePost {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        URL url = new URL("http://localhost:8080");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(
                        "firstname=Luan&lastname=Nguyen"
                        ))
                .uri(url.toURI())
                .header("Content-Type","application/x-www-form-urlencoded")
                .build();
        HttpResponse<Stream<String>> httpResponse = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofLines());
        if(httpResponse.statusCode() != HTTP_OK){
            System.out.println("Err:" + url);
            return;
        }
        httpResponse.body()
                .filter(s -> s.contains("<h1>"))
                .forEach(System.out::println);

    }
}
