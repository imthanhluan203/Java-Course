package Section23.ClientServer.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SimpleServerChanel {
    public static void main(String[] args) {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(5000));
            socketChannel.configureBlocking(false);
            System.out.println("Server listening on port: " + socketChannel.socket().getLocalPort());
            List<SocketChannel> sockets = new ArrayList<>();
            while (true){
                //System.out.println("Waiting to connect to another client");
                SocketChannel clientChannelAdd = socketChannel.accept();
                if(clientChannelAdd!=null) {
                    clientChannelAdd.configureBlocking(false);
                    System.out.println("Client connected to Server: "+clientChannelAdd.socket().getRemoteSocketAddress());
                    sockets.add(clientChannelAdd);
                }
                for(int i=0;i<sockets.size();i++){
                    SocketChannel clientChannel = sockets.get(i);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //System.out.println("Waiting to connect to request from client");
                    int readBytes = clientChannel.read(buffer);
                    if(readBytes > 0){
                        buffer.flip();
                        clientChannel.write(ByteBuffer.wrap("Echo from server:".getBytes()));
                        while(buffer.hasRemaining()){
                            clientChannel.write(buffer);
                        }
                        buffer.clear();
                    } else if (readBytes == -1) {
                        System.out.println(clientChannel.socket().getRemoteSocketAddress() + "connection lost");
                        clientChannel.close();
                        sockets.remove(i);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
