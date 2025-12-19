package MonstreDePoche.models.monsters;

import static MonstreDePoche.views.ConsoleEffects.*;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.Effect;
import MonstreDePoche.models.attacks.Attack;

public class Monster {
    public String name;
    public Type type;
    public int hp_max;
    public int hp;
    public int attack;
    public int defense;
    public int speed;
    public Attack[] attacks;
    public Effect currentEffect;
    public String caracteristicSpecial;

    public Monster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        this.name = name;
        this.hp_max = hp;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public Monster(MonsterChoice choice) {
        this.name = choice.getName();
        this.hp_max = choice.getRandomHp();
        this.hp = this.hp_max;
        this.attack = choice.getRandomAttack();
        this.defense = choice.getRandomDefense();
        this.speed = choice.getRandomSpeed();  
        this.caracteristicSpecial = choice.getCaracteristicSpecial();  
    }

    @Override
    public String toString() {
        return name +
                " {hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", speed=" + speed +
                ", type=" + type +
                '}';
    }

    public void attack(Monster target, Attack attack) {
    }

    public void receiveDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public int getHpMax() {
        return hp_max;
    }

    public int getHp() {
        return hp;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public String getColor(){
        Type type = this.type;
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

    public Attack[] getAttacks() {
        return attacks;
    }

    public Type getType() {
        return type;
    }

    public void setAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }
}