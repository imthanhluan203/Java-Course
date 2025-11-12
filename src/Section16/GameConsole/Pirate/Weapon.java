package Section16.GameConsole.Pirate;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum Weapon {
    KNIZE(0,10),
    AXE(0,30),
    MACHETE(1,40),
    PISTOL(1,50);
    private final int minLevel;
    private final int hitPoints;
    Weapon(int minLevel,int hitPoints){
        this.minLevel = minLevel;
        this.hitPoints = hitPoints;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    public static Weapon getWeaponByChar(char fistInitial){
        for(Weapon c : Weapon.values()){
            if(c.name().charAt(0) == fistInitial){
                return c;
            }
        }
        return values()[0];
    }
    public static List<Weapon> getWeaponsByLevel(int levelOfPlay){
        List<Weapon> weapons = new ArrayList<>(EnumSet.allOf(Weapon.class));
        weapons.removeIf(x->x.minLevel > levelOfPlay);
        return weapons;
    }
}
