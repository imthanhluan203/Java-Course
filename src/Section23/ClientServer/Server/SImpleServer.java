package Section23.ClientServer.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SImpleServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            while (true){
                //Để main ko bị thoát
                //nếu ko có while chương trình ko thể tạo nhiều kết nối của
                //client đến server
                try {
                    Socket socket = serverSocket.accept();
                    //treo ngay tại đây đợi client connect đến server
                    //rồi mới chạy tiếp
                    executorService.submit(()->{
                        handleConnection(socket);
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

//            System.out.println("Server accepts client connection");
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
//            while (true){
//                String echoString = input.readLine();
//                System.out.println("Server got request data: "+echoString);
//                if(echoString.equals("exit")){
//                    System.out.println("Server exit");
//                    break;
//                }
//                output.println("Echo from server: " + echoString);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handleConnection(Socket socket){
        try{
            System.out.println("Server accepts client connection");
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                String echoString = input.readLine();
                System.out.println("Server got request data: "+echoString);
                if(echoString.equals("exit")){
                    System.out.println("Server exit");
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
