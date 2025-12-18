package MonstreDePoche.models.monsters;

import MonstreDePoche.models.Type;

public class MonsterChoice {
    private String name;
    private Type type;
    private int hp_max;
    private int hp_min;
    private int attack_max;
    private int attack_min;
    private int defense_max;
    private int defense_min;
    private int speed_max;
    private int speed_min;

    public MonsterChoice(String name, Type type, int hp_min, int hp_max, int attack_min, int attack_max, int defense_min, int defense_max, int speed_min, int speed_max) {
        this.name = name;
        this.type = type;
        this.hp_max = hp_max;
        this.hp_min = hp_min;
        this.attack_max = attack_max;
        this.attack_min = attack_min;
        this.defense_max = defense_max;
        this.defense_min = defense_min;
        this.speed_max = speed_max;
        this.speed_min = speed_min;
    }

    public String getSmallInformation(){
        return name + " : " + type;
    }

    public String getFullInformation(){
        return name + " : " + type +
                "\nHP : " + hp_min + " - " + hp_max +
                "\nAttack : " + attack_min + " - " + attack_max +
                "\nDefense : " + defense_min + " - " + defense_max +
                "\nSpeed : " + speed_min + " - " + speed_max;
    }
}
