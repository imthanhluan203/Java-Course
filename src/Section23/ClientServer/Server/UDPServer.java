package Section23.ClientServer.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        try {
            UDPServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UDPServer() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        System.out.println("Server is running");
        while (true){
            byte[] buffer = new byte[1024];
            DatagramPacket datagramPacket =new DatagramPacket(buffer,buffer.length);
            datagramSocket.receive(datagramPacket);

            String request = new String(buffer).trim();
            System.out.println("Received message: " + request);
            InetAddress inetAddressCLient = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();
            String response = "OKE";
            buffer = response.getBytes();
            datagramPacket = new DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(),clientPort);
            datagramSocket.send(datagramPacket);
        }
    }
}
