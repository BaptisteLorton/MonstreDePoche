package MonstreDePoche.models.monsters;

import static MonstreDePoche.views.ConsoleEffects.*;

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

    private String getColor(Type type){
        switch (type) {
            case Type.FIRE:
                return RED;
            case Type.WATER:
                return BLUE;
            case Type.GRASS:
                return GREEN;
            case Type.INSECT:
                return LIME;
            case Type.ELECTRIC:
                return YELLOW;
            case Type.GROUND:
                return BROWN;
            default:
                return RESET;
        }
    }

    public String getSmallInformation(){
        return name + " : " + getColor(type) + type + RESET;
    }

    public String getFullInformation(){
        return name + " : " + getColor(type) + type + RESET +
                "\nHP : " + hp_min + " - " + hp_max +
                "\nAttack : " + attack_min + " - " + attack_max +
                "\nDefense : " + defense_min + " - " + defense_max +
                "\nSpeed : " + speed_min + " - " + speed_max;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getHp_max() {
        return hp_max;
    }

    public int getHp_min() {
        return hp_min;
    }

    public int getAttack_max() {
        return attack_max;
    }

    public int getAttack_min() {
        return attack_min;
    }

    public int getDefense_max() {
        return defense_max;
    }

    public int getDefense_min() {
        return defense_min;
    }

    public int getSpeed_max() {
        return speed_max;
    }

    public int getSpeed_min() {
        return speed_min;
    }

    public int getRandomHp(){
        return (int) (Math.random() * (hp_max - hp_min + 1)) + hp_min;
    }

    public int getRandomAttack(){
        return (int) (Math.random() * (attack_max - attack_min + 1)) + attack_min;
    }

    public int getRandomDefense(){
        return (int) (Math.random() * (defense_max - defense_min + 1)) + defense_min;
    }

    public int getRandomSpeed(){
        return (int) (Math.random() * (speed_max - speed_min + 1)) + speed_min;
    }
}