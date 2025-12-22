package MonstreDePoche.models;

import java.util.ArrayList;

import MonstreDePoche.models.monsters.Monster;
import MonstreDePoche.models.objects.ObjectToUse;

public class Player {
    private String name;
    private Monster activeMonster;
    private Monster[] monsters = new Monster[3];
    private ArrayList<ObjectToUse> objects = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Monster getActiveMonster() {
        return activeMonster;
    }

    public void setActiveMonster(Monster monster) {
        this.activeMonster = monster;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
        this.activeMonster = monsters[0];
    }

    public void setObjects(ArrayList<ObjectToUse> objects) {
        this.objects = objects;
    }

    public ArrayList<ObjectToUse> getObjects() {
        return objects;
    }

    public boolean canPlay() {
        for (Monster monster : monsters) {
            if (monster.getHp() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAvailableMonsters() {
        for (Monster monster : monsters) {
            if (monster.getHp() > 0) {
                return true;
            }
        }
        return false;
    }
}
