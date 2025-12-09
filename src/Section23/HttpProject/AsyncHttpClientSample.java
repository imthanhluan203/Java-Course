package Section23.HttpProject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

import static java.net.HttpURLConnection.HTTP_OK;

public class AsyncHttpClientSample {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        URL url = new URL("http://localhost:8080");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(url.toURI())
                .header("Content-Type","application/x-www-form-urlencoded")
                .build();
        HttpResponse<Stream<String>> httpResponse;
        var httpResponseFuture = httpClient.sendAsync(httpRequest,HttpResponse.BodyHandlers.ofLines());
        httpResponseFuture.thenAccept(r -> handleResponse(r))
                .thenRun(System.out::println)
                .thenRun(System.out::println)
                .thenRun(System.out::println)
                .thenRun(System.out::println);
        int jobcount = 0;
        while (jobcount++<10){
            System.out.println("Job: " + jobcount);
            Thread.sleep(700);
        }

    }
    public static void handleResponse(HttpResponse<Stream<String>> httpResponse){
        if(httpResponse.statusCode() != HTTP_OK){
            System.out.println("Err:" + httpResponse.uri());
            return;
        }
        httpResponse.body()
                .filter(s -> s.contains("<h1>"))
                .forEach(System.out::println);
    }
}
