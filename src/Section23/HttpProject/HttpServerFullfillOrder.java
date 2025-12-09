package Section23.HttpProject;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

public class HttpServerFullfillOrder {
    private static AtomicLong lastID = new AtomicLong(1);
    private static long getNextID(){
        return lastID.getAndIncrement();
    }
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8081),0);
            httpServer.createContext("/",e -> {
                String reqParameter = e.getRequestURI().toString();
                System.out.printf("%s %s %s%n",
                        e.getRemoteAddress(),
                        e.getRequestMethod(),
                        reqParameter);
                String requestMethod = e.getRequestMethod();
                String data = "";
                String response ="";
                int responseCode = HTTP_OK;
                if(requestMethod.equals("GET")){
                    data = reqParameter.substring(reqParameter.indexOf("?")+1);
                } else if (requestMethod.equals("POST")) {
                    data = new String(e.getRequestBody().readAllBytes());
                }
                System.out.println("Request body:" + data);
                Map<String,String> dataParsed = parsedData(data);
                System.out.println(dataParsed);
                if(dataParsed.size() ==2){
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime delivery = now.plusDays(3);
                    response = """
                            {
                                "order":
                                    {
                                        "orderID":"%010d",
                                        "product":"%s",
                                        "amount":"%s",
                                        "orderReceived":"%s",
                                        "orderDeliveryDate":"%s"
                                    }
                            }
                            """.formatted(getNextID(),
                                            dataParsed.get("product"),
                                            dataParsed.get("amount"),
                                            now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                                            delivery.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                            .replaceAll("\\s","");
                }else {
                    response = "{\"result\":\"Bad data is sent\"}";
                    responseCode = HTTP_BAD_REQUEST;
                }

                e.getRequestHeaders().entrySet().forEach(System.out::println);
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                e.sendResponseHeaders(responseCode,bytes.length);
                e.getResponseBody().write(bytes);
                e.close();
            });
            httpServer.start();
            System.out.println("Server is listening on port 8080");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static Map<String,String> parsedData(String body){
        Map<String,String> dataP = new HashMap<>();
        String[] pairs = body.split("&");
        for(var pair : pairs){
            String[]kv = pair.split("=");
            if(kv.length == 2){
                dataP.put(kv[0],kv[1]);
            }
        }
        return dataP;
    }
}
