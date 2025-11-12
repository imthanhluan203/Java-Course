package Section16.GameConsole;

import java.util.Scanner;

public class GameConsole<T extends Game<? extends Player>> {
    private final T game;
    private static final Scanner scanner = new Scanner(System.in);

    public GameConsole(T game) {
        this.game = game;
    }
    public int addPlayer(){
        System.out.println("Enter your playing name: ");
        String name = scanner.nextLine();
        System.out.printf("Welcome to %s, %s! %n",game.getGameName(),name);
        return game.addPlayer(name);
    }
    public void playGame(int playerIndex){
        boolean done = false;
        while (!done){
            var gameActions = game.getGameActions(playerIndex);
            System.out.println("Select from one of the following Actions: ");
            for(Character c : gameActions.keySet()){
                String promt = gameActions.get(c).prompt();
                System.out.println("\t" + promt + " (" + c + ")");
            }
            System.out.print("Enter next action: ");
            char nextMove = scanner.nextLine().toUpperCase().charAt(0);
            GameAction gameAction = gameActions.get(nextMove);
            if(gameAction != null){
                System.out.println("-".repeat(50));
                done = game.executeGameAction(playerIndex,gameAction);
//                public boolean executeGameAction(int player,GameAction action){
//                    return action.action().test(player);
//                }
                if(!done){
                    System.out.println("-".repeat(50));
                }
            }
        }
    }
}
