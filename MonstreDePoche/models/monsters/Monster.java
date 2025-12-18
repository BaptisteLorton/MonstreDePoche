package MonstreDePoche.models.monsters;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.Effect;
import MonstreDePoche.models.attacks.Attack;

public class Monster {
    public String name;
    public Type type;
    public int hp;
    public int attack;
    public int defense;
    public int speed;
    public Attack[] attacks;
    public Effect currentEffect;

    public Monster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public Monster(MonsterChoice choice) {
        this.name = choice.getName();
        this.hp = choice.getRandomHp();
        this.attack = choice.getRandomAttack();
        this.defense = choice.getRandomDefense();
        this.speed = choice.getRandomSpeed();    
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

    public void receiveDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
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
}