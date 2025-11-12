package Section16.GameConsole;

import Section16.GameConsole.Pirate.Pirate;
import Section16.GameConsole.Pirate.PirateGame;
import Section16.GameConsole.Pirate.Weapon;

public class Main {
    public static void main(String[] args) {
//        var console = new GameConsole<>(new ShooterGame("The Shootout Game"));
//        int playerIndex = console.addPlayer();
//        console.playGame(playerIndex);
        Weapon weapon = Weapon.getWeaponByChar('P');
        System.out.println("weapon = %s, hitPoints = %s, minLevel = %s"
                .formatted(weapon,weapon.getHitPoints(),weapon.getMinLevel()));
        var list = Weapon.getWeaponsByLevel(1);
        list.forEach(x->System.out.println(x));

        Pirate Tim = new Pirate("Tim");
        System.out.println(Tim);
        System.out.println("=".repeat(30));
        PirateGame.getTowns(1).forEach(x->System.out.println(x));

        var console = new GameConsole<>(new PirateGame("The Pirate Game"));
        int playerIndex = console.addPlayer();
        console.playGame(playerIndex);
    }
}
