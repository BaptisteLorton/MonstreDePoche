package MonstreDePoche.models.monsters;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.attacks.Attack;

public class Monster {
    public String name;
    public Type type;
    public int hp;
    public int attack;
    public int defense;
    public int speed;
    public Attack[] attacks;

    public Monster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return name +" {" +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed='" + speed + '\'' +
                ", type=" + type +
                '}';
    }
}