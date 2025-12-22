package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;

import java.util.concurrent.ThreadLocalRandom;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.*;

public class ElectricMonster extends Monster {
    public ElectricMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.ELECTRIC;
    }

    public ElectricMonster(MonsterChoice choice) {
        super(choice);
        this.type = Type.ELECTRIC;
    }

    @Override
    public void attack(Monster target, Attack attack) {
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
                    //Test paralysis effect application
                    if(attack.getType() == Type.ELECTRIC){
                        boolean valid = this.receiveParalysis(this.caracteristicSpecial);
                        if(valid == true){
                            System.out.println(" paralysis ok");
                            target.currentEffect = new EffectParalyze();
                        }
                        else{
                            System.out.println(" paralysis failed");
                        }
                    }
                    if (this.getHp() > 0) {
                        int damage = attack.getPower();
                        if (target instanceof WaterMonster){
                            damage = attack.getPower()*2;
                        } else if (target instanceof GroundMonster){
                            damage = attack.getPower()/2;
                        }
                        target.receiveDamage(damage);
                    }
                    }

                return;
            }
        }
        else if(this.currentEffect instanceof EffectBurn){
            int damageBurn = this.attack /10;
            this.receiveDamage(damageBurn);
            System.out.println(this.name + " is affected by burn and loses " + damageBurn + " HP.");

            if (this.getHp() > 0) {
                int damage = attack.getPower();
                if (target instanceof WaterMonster){
                    damage = attack.getPower()*2;
                } else if (target instanceof GroundMonster){
                    damage = attack.getPower()/2;
                }
                target.receiveDamage(damage);
            }
        }
        else if(this.currentEffect instanceof EffectPoison){
            int damagePoison = this.attack /10;
            this.receiveDamage(damagePoison);
            System.out.println(this.name + " is affected by poison and loses " + damagePoison + " HP.");
               
            if (this.getHp() > 0) {
                    int damage = attack.getPower();
                    if (target instanceof WaterMonster){
                        damage = attack.getPower()*2;
                    } else if (target instanceof GroundMonster){
                        damage = attack.getPower()/2;
                    }
                    target.receiveDamage(damage);
                }

        }
        else{
            
            if(attack.getType() == Type.ELECTRIC){
                boolean valid = this.receiveParalysis(this.caracteristicSpecial);
                if(valid == true){
                        System.out.println(" paralysis succeded");
                        target.currentEffect = new EffectParalyze();
                    }
                    else{
                        System.out.println(" paralysis failed");
                        }
            }

            if (this.getHp() > 0) {
                int damage = attack.getPower();
                if (target instanceof WaterMonster){
                    damage = attack.getPower()*2;
                } else if (target instanceof GroundMonster){
                    damage = attack.getPower()/2;
                }
                target.receiveDamage(damage);
            }
                    
        }
    }
}
