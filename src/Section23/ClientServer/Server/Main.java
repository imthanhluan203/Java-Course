package Section23.ClientServer.Server;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Consumer<ByteBuffer> readAndPrint = (buffer) -> {
            byte[] data = new byte[buffer.limit()];
            buffer.get(data);
            String output = new String(data, StandardCharsets.UTF_8);
            System.out.printf("\"%s\" ".formatted(output));
        };
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        doOperation("Print: ",buffer,b -> System.out.print(b + " "));
        doOperation("Write: ",buffer,b -> b.put("This is a test case".getBytes()));
        doOperation("Read state: ",buffer, b -> b.flip());
        doOperation("Read and Print",buffer,readAndPrint);
        doOperation("Write state: ",buffer, b -> b.flip());
//        doOperation("Write state: ",buffer, b -> b.position(19));
//        doOperation("Set capacity: ",buffer,b -> b.limit(b.capacity()));
        doOperation("Set capacity: ",buffer,b -> b.compact());
        doOperation("Write: ",buffer,b -> b.put(" This is a new test case".getBytes()));
        doOperation("Read and Print",buffer.slice(0,buffer.position()),readAndPrint);
    }
    private static void doOperation(String op, ByteBuffer buffer, Consumer<ByteBuffer> consumer){
        System.out.printf("%-30s",op);
        consumer.accept(buffer);
        System.out.printf("Capacity = %d, Limit = %d, Position = %d, Remaining = %d%n",
                buffer.capacity(),
                buffer.limit(),
                buffer.position(),
                buffer.remaining());
    }
}
