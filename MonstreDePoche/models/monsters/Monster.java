package MonstreDePoche.models.monsters;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.Effect;
import MonstreDePoche.models.Effects.EffectBurn;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.Effects.EffectParalyze;
import MonstreDePoche.models.Effects.EffectPoison;



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
        this.currentEffect = null;  
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
        if (currentEffect instanceof EffectParalyze) {
            return name + " (Paralyzed)";
        }
        else if (currentEffect instanceof EffectBurn) {
            return name + " (Burned)";    
        }
        else if(currentEffect instanceof EffectPoison){
            return name + " (Poisoned)";
        }
        else{
            return name;
        }
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

    public boolean receiveParalysis(String car) {
        String[] temp = car.split(" ");

        double chance = Double.parseDouble(temp[1]);
        if (Math.random() < chance) {
           return true;
        } else {
           return false;
        }   
    }

    public boolean receiveBurn(String car){
        String[] temp = car.split(" ");
        double valeur = ThreadLocalRandom.current().nextDouble(0.10, 0.50);
        double chance = (Double.parseDouble(temp[1])*valeur)/2; //moy entra la stat du monstre et une valeur aleatoire entre 0.10 et 0.50

        double statBurn = ThreadLocalRandom.current().nextDouble(0.0, 0.70); 

        //si chance > à statBurn alors le monstre est brulé
        if (chance > statBurn){
            return true;
        } else {
            return false;
        }
    }

    public boolean receivePoison(){
        double valeur = ThreadLocalRandom.current().nextDouble(0.01, 0.99);
        //si chance <= à 0.33 (une chance sur 3) alors le monstre est empoisonné
        if (valeur <= 0.33){
            return true;
        } else {
            return false;
        }
    }

    public int goTunnel(){
        Random rand = new Random();
        int tirage = rand.nextInt(100);
        if (tirage<=5){
            System.out.println( this.name + "enter in the tunnel");
            Random nbrTour = new Random();
            return nbrTour.nextInt(3)+1; // Génère un nombre aléatoire entre 1 et 3
        }
        else{
            return 0;
        }
    }
        
    

    public void increaseHeal(int healBoost) {
        this.hp += healBoost;
        if (this.hp > this.hp_max) {
            this.hp = this.hp_max;
        }
    }

    public void increaseAttack(int attackBoost) {
        this.attack += attackBoost;
    }

    public void increaseAttacks(int attackBoost) {
        for (Attack atk : this.attacks) {
            atk.increasePower(attackBoost);
        }
    }

    public void increaseDefense(int defenseBoost) {
        this.defense += defenseBoost;
    }

    public void increaseSpeed(int speedBoost) {
        this.speed += speedBoost;
    }
}