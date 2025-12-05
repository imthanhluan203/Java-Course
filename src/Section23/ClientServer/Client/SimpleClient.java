package Section23.ClientServer.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("Enter your request:");
                String request = scanner.nextLine();
                output.println(request);
                if(request.equalsIgnoreCase("exit")){
                    System.out.println("Client exit");
                    break;
                }
                String respond = input.readLine();
                System.out.println("Respond from server: " + respond);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
