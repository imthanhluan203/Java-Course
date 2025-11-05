package Section12.GenericTheory;
interface Player{
    String name();
}
record BaseballPlayer(String name, String position) implements Player {}
record FootballPlayer(String name, String position) implements Player {}

public class Main {

    public static void main(String[] args) {

        BaseballTeam phillies1 = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros1 = new BaseballTeam("Houston Astros");
        scoreResult(phillies1, 3, astros1, 5);

        SportTeam phillies2 = new SportTeam("Philadelphia Phillies");
        SportTeam astros2 = new SportTeam("Houston Astros");
        scoreResult(phillies2, 3, astros2, 5);

        Team<BaseballPlayer> phillies = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer> astros = new Team<>("Houston Astros");
        scoreResult(phillies, 3, astros, 5);

        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        var guthruie = new BaseballPlayer("D Futheie","Center Field");
        phillies.addTeamMember(guthruie);
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        phillies.listTeamMembers();

        SportTeam afc1 = new SportTeam("Adelaide Crows");
        Team<FootballPlayer> afc = new Team<>("Adelaide Crows");
        var tex = new FootballPlayer("Tex Walker","Centre haft forward");
        afc.addTeamMember(tex);
        var rory = new FootballPlayer("Rory Laird","Midfield");
        afc.addTeamMember(rory);
        afc.listTeamMembers();

//        Team<String> adelaide = new Team<>("Adelaide Storm");
//        adelaide.addTeamMember("N Roberts");
//        adelaide.listTeamMembers();
//        Team<String> canberra = new Team<>("Canberra Heat");
//        adelaide.addTeamMember("B Black");
//        adelaide.listTeamMembers();
//        scoreResult(canberra,0,adelaide,1);
    }

    public static void scoreResult(BaseballTeam team1, int t1_score,
                                   BaseballTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
    public static void scoreResult(SportTeam team1, int t1_score,
                                   SportTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
    public static void scoreResult(Team team1, int t1_score,
                                   Team team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}
