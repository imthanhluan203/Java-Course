package Section23.HttpProject;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public class HttpServerSimple {
    private static int count = 0;
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080),0);
            httpServer.createContext("/",e -> {
                String requestMethod = e.getRequestMethod();
                System.out.println("Request Method: "+requestMethod);
                String data = new String(e.getRequestBody().readAllBytes());
                System.out.println("Request body:" + data);
                Map<String,String> dataParsed = parsedData(data);
                System.out.println(dataParsed);
                if(requestMethod.equals("POST")){
                    HttpServerSimple.count++;
                }
                // Lấy giá trị đã nhập (nên kiểm tra null trước khi sử dụng)
                String firstName = dataParsed.get("firstname") == null ? "":dataParsed.get("firstname");
                String lastName = dataParsed.get("lastname")  == null ? "":dataParsed.get("lastname");
                String response = """
                        <html>
                            <body>
                                <h1>Respond is sent from server</h1>
                                <p>Number of visitor: %d</p>
                                <form method = "POST">
                                <label for="firstname">firstname:</label>
                                <input type="text" id="firstname" name="firstname" value = "%s">
                                <br>
                                <label for="lastname">lastname:</label>
                                <input type="text" id="lastname" name="lastname" value = "%s">
                                <br>
                                <button>SUBMIT</button>
                                </form>
                            </body>
                        </html>
                        """.formatted(count,
                        firstName,
                        lastName);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                e.getRequestHeaders().entrySet().forEach(System.out::println);
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                e.sendResponseHeaders(HTTP_OK,bytes.length);
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
