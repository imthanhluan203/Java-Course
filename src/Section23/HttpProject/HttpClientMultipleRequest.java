package Section23.HttpProject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HttpClientMultipleRequest {
    private static String filePost = "data.json";
    public static void main(String[] args) throws URISyntaxException {
        Map<String,String> data = Map.of(
                "Aosomi","10",
                "QuanKaki", "5",
                "XeMay","15",
                "LapTop","20"
                );
        String requestBase = "product=%s&amount=%s";
        String uriBase = "http://localhost:8081";
        List<URI> uris = new ArrayList<>();
        data.forEach((k,v) -> {
            String temp = uriBase + "?" +requestBase.formatted(k,v);
            try {
                uris.add(new URI(temp));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        HttpClient client = HttpClient.newHttpClient();
        sendRequestWithGet(uris,client);
        sendRequestWithPost(new URI(uriBase),client,requestBase,data);
    }
    private static void sendRequestWithGet(List<URI>uri, HttpClient client) {
        var httpRequestStream = uri.stream()
                .map(x->HttpRequest.newBuilder(x).GET())
                .map(HttpRequest.Builder::build)
                .map(x -> client.sendAsync(x, HttpResponse.BodyHandlers.ofString()));

        var t = httpRequestStream.map(CompletableFuture::join);
        t.forEach(x -> System.out.println(x.body()));
    }
    private static void sendRequestWithPost(URI baseuri, HttpClient client,
                                            String param,Map<String,String> data) {
        var t = data.entrySet().stream()
                .map((e -> param.formatted(e.getKey(),e.getValue())))
                .map(e -> HttpRequest.newBuilder(baseuri).POST(HttpRequest.BodyPublishers.ofString(e)))
                .map(e->e.build())
                .map(e->client.sendAsync(e,HttpResponse.BodyHandlers.ofString()));
        var postEx = t.map(x->x.join());
        var listData = postEx.map(x->x.body()).toList();
        try {
            Files.write(Path.of(filePost),listData, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
