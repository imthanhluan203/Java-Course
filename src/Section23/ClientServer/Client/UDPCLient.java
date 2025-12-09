package Section23.ClientServer.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPCLient {
    public static void main(String[] args) {
        try {
            UDPClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UDPClient() throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] buffer = "Hello World".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length, InetAddress.getLocalHost(),1234);
        datagramSocket.send(datagramPacket);
        buffer = new byte[1024];
        datagramPacket = new DatagramPacket(buffer,buffer.length);
        datagramSocket.receive(datagramPacket);
        System.out.println("Received message from server: " + new String(buffer).trim());
    }
}
