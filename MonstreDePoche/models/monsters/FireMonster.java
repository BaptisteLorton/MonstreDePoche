package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.views.Interface;
import MonstreDePoche.models.attacks.StruggleAttack;

import java.util.concurrent.ThreadLocalRandom;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.EffectBurn;
import MonstreDePoche.models.Effects.EffectParalyze;
import MonstreDePoche.models.Effects.EffectPoison;

public class FireMonster extends Monster {
    public FireMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.FIRE;
    }

    public FireMonster(MonsterChoice choice) {
        super(choice);
        this.type = Type.FIRE;
    }

    @Override
    protected double getAvantage(Monster target, Attack attack){
        if (attack.getType() == Type.FIRE || attack instanceof StruggleAttack){
            if (target instanceof GrassMonster){
                return 2.0;
            } else if (target instanceof WaterMonster){
                return 0.5;
            }
        }
        return 1.0;
    }

    @Override
    public void attack(Monster target, Attack attack) {
        if(this.currentEffect instanceof EffectBurn && Interface.land.flooded == true){
            this.currentEffect = null;
        }

        if(this.currentEffect instanceof EffectParalyze){
            System.out.println(this.name + " is affected by paralysis.");
            EffectParalyze paralyse = (EffectParalyze) this.currentEffect;
            int radomRemoveParalysis = ThreadLocalRandom.current().nextInt(1, paralyse.paralyseDuration + 1);
            //test remove paralysis effect
            if(radomRemoveParalysis ==1){
                this.currentEffect = null;
                System.out.println(this.name + " is no longer paralyzed.");
            } else {
                paralyse.paralyseDuration -= 1;
                System.out.println(this.name + " is paralyzed ");
                int RandomParalysis = ThreadLocalRandom.current().nextInt(1, 5);
                if(RandomParalysis !=1){
                    System.out.println(this.name + " cannot move!");
                }
                else{
                    System.out.println(this.name + " Succed attack!");
                    if(attack.getType() == Type.FIRE){
                        boolean valid = this.receiveBurn(this.caracteristicSpecial);
                        if(valid == true){
                            System.out.println("Burn ok");
                            target.currentEffect = new EffectBurn();
                        }
                        else{
                            System.out.println("Burn failed");
                        }
                    }
                    if (this.getHp() > 0) {
                        int damage;
                        if (attack instanceof StruggleAttack){
                            damage = getDamageStruggle(target, attack);
                        } else {
                            damage = getDamage(target, attack);
                        }
                        attack.useAttack();
                        target.receiveDamage(damage);
                    }
                    }

            }
        }
        else if(this.currentEffect instanceof EffectBurn){
            int damageBurn = this.attack /10;
            this.receiveDamage(damageBurn);
            System.out.println(this.name + " is affected by burn and loses " + damageBurn + " HP.");



            if (this.getHp() > 0) {
                int damage;
                if (attack instanceof StruggleAttack){
                    damage = getDamageStruggle(target, attack);
                } else {
                    damage = getDamage(target, attack);
                }
                attack.useAttack();
                target.receiveDamage(damage);
            }
        }
        else if(this.currentEffect instanceof EffectPoison){
            int damagePoison = this.attack /10;
            this.receiveDamage(damagePoison);
            System.out.println(this.name + " is affected by poison and loses " + damagePoison + " HP.");
               
            if (this.getHp() > 0) {
                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);
                }

        }
            else{
                if(attack.getType() == Type.FIRE){
                    boolean valid = this.receiveBurn(this.caracteristicSpecial);
                    if(valid == true){
                            System.out.println(" Burn ok");
                            target.currentEffect = new EffectBurn();
                        }
                        else{
                            System.out.println(" Burn failed");
                        }
                    }
                    if (this.getHp() > 0) {
                        int damage;
                        if (attack instanceof StruggleAttack){
                            damage = getDamageStruggle(target, attack);
                        } else {
                            damage = getDamage(target, attack);
                        }
                        attack.useAttack();
                        target.receiveDamage(damage);
                    }
                }
    }
}
