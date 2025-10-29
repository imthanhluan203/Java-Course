package Section5;

public class OverLoadingChallenge_2 {
    public static void main(String[] args) {
        System.out.print(getDurationString(19275));
        System.out.print(getDurationString(321,15));
    }
    public static String getDurationString(int seconds){
        if(seconds < 0){
            return "Invalid Value";
        }
        int hour = seconds / 3600;
        int minute = (seconds - hour*3600) / 60;
        int second = seconds - hour*3600 - minute*60;
        return hour + "h " + minute + "m " +second + "s\n";
    }
    public static String getDurationString(int minutes,int seconds) {
        if(minutes < 0 || seconds<0 || seconds > 59){
            return "Invalid Value";
        }
        return getDurationString(minutes*60 + seconds);
    }
}
