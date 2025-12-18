package MonstreDePoche.models;

import MonstreDePoche.models.monsters.Monster;

public class Player {
    private String name;
    private Monster[] monsters = new Monster[3];

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public boolean canPlay() {
        for (Monster monster : monsters) {
            if (monster.getHp() > 0) {
                return true;
            }
        }
        return false;
    }
}
