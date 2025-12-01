package Section21.ThreadProblem;

import java.util.Arrays;

record Participant(String name, String searchingFor,Maze maze,int[] startingPosition){
    Participant{
        maze.moveLocation(startingPosition[0],startingPosition[1],name);
    }
}
class ParticipantThread implements Runnable{
    public final Participant participant;

    public ParticipantThread(Participant participant) {
        this.participant = participant;
        Thread.currentThread().setName(participant.name());
    }

    @Override
    public void run() {
        int[] lastSpot = participant.startingPosition();
        Maze maze = participant.maze();
        while (true){
            int[] newSpot = maze.getNextLocation(lastSpot);
            try {
                Thread.sleep(participant.name().equals("Grace")?2900 : 500);
                if(maze.searchCell(participant.searchingFor(),newSpot,lastSpot)){
                    System.out.printf("%s found %s at %s!%n"
                            ,participant.name(),participant.searchingFor(), Arrays.toString(newSpot));
                    break;
                }
                synchronized (maze){
                    maze.moveLocation(newSpot[0],newSpot[1], participant.name());
               }
                lastSpot = new int[]{newSpot[0],newSpot[1]};
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(participant.name() + " searching " + participant.maze());
        }
    }
}
public class MazeRunner {
    public static void main(String[] args) {
        Maze maze = new Maze();
        Participant adam = new Participant("Adam","Grace",maze,new int[]{3,3});
        Participant graze = new Participant("Grace","Adam",maze,new int[]{1,1});
        System.out.println(maze);
        Thread adamThread = new Thread(new ParticipantThread(adam),"ADAM_THREAD");
        Thread grazeThread = new Thread(new ParticipantThread(graze),"GRAZE_THREAD");

        adamThread.start();
        grazeThread.start();
        while (adamThread.isAlive() && grazeThread.isAlive()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(!adamThread.isAlive()){
            grazeThread.interrupt();
        } else if (!grazeThread.isAlive()) {
            adamThread.interrupt();
        }
    }
}
