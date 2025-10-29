package Section5;

public class KeywordAndExpression {
    public static void main(String[] args) {
        int score1500 = calculateHighScorePosition(1500);
        int score1000 = calculateHighScorePosition(1000);
        int score500 = calculateHighScorePosition(500);
        int score100 = calculateHighScorePosition(100);
        int score25 = calculateHighScorePosition(25);
        displayHighScorePosition("Tim",score1500);
        displayHighScorePosition("Tim",score1000);
        displayHighScorePosition("Tim",score500);
        displayHighScorePosition("Tim",score100);
        displayHighScorePosition("Tim",score25);

        displayHighScorePosition("Tim", 2);
    }
    public static  void displayHighScorePosition(String playerName,int playerPosition){
        System.out.print(playerName + " managed to get into position "+playerPosition+" on the high score list \n");
    }
    public static int calculateHighScorePosition(int playerScore){
        if(playerScore >= 1000){
            return 1;
        } else if (playerScore < 1000 && playerScore >=500 ) {
            return 2;
        }else if (playerScore < 500 && playerScore >=100 ) {
            return 3;
        }else {
            return 4;
        }
    }
}
