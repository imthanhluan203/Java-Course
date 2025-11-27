package Section21.ConsumerProducer;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageResoisitory{
    private String message;
    private boolean hasMessage = false;
    private Lock lock = new ReentrantLock();
    public String read(){
        if(lock.tryLock()) {
            try{
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = false;
            }finally {
                lock.unlock();
            }
        }else {
            System.out.println("**Read blocked " + lock);
            hasMessage = false;
        }
        return message;
    }
    public void write(String message){
        if(lock.tryLock()){
            try{
                while (hasMessage){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            }finally {
                lock.unlock();
            }
        }else{
            System.out.println("** Write blocked " + lock);
            hasMessage = true;
        }
        this.message = message;
    }
}
class MessageWriter implements Runnable{
    private MessageResoisitory outgoingMessage;
    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses and all the king's men,
            Couldn't put Humpty together again.""";

    public MessageWriter(MessageResoisitory outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");
        for(int i=0;i<lines.length;i++){
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500,2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        outgoingMessage.write("Finished");
    }
}
class MessageReader implements Runnable{
    private MessageResoisitory incomeMessage;

    public MessageReader(MessageResoisitory incomeMessage) {
        this.incomeMessage = incomeMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage ="";
        do {
            try {
                Thread.sleep(random.nextInt(500,2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomeMessage.read();
            System.out.println(latestMessage);
        }while (!latestMessage.equals("Finished"));
    }
}
public class Main {
    public static void main(String[] args) {
        MessageResoisitory messageResoisitory = new MessageResoisitory();
        Thread reader = new Thread(new MessageReader(messageResoisitory),"Reader");
        Thread writer = new Thread(new MessageWriter(messageResoisitory),"Writer");

        reader.start();
        writer.start();
    }
}
