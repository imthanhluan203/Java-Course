package Section23.ClientServer.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorChannelServer {
    public static void main(String[] args) {
        try (ServerSocketChannel socketChannel = ServerSocketChannel.open()) {
            socketChannel.bind(new InetSocketAddress(5000));
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                while (selectionKeyIterator.hasNext()){
                    var key = selectionKeyIterator.next();
                    if(key.isAcceptable()){
                        SocketChannel client = socketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);
                        System.out.println("Client connected: " + client.getRemoteAddress());
                    }
                    if(key.isReadable()){
                        answerWithEcho(buffer,key);
                    }
                    selectionKeyIterator.remove();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void answerWithEcho(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        int r = client.read(buffer);
        if(r > 0){
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String answer = "Echo from Server: " + new String(data);
            client.write(ByteBuffer.wrap(answer.getBytes()));
            buffer.clear();
        }else {
            System.out.println("Client disconnected: " + client.getRemoteAddress());
            key.cancel();;
            client.close();
        }
    }
}
