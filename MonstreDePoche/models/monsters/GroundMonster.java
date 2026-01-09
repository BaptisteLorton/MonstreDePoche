package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.views.Interface;
import MonstreDePoche.models.attacks.StruggleAttack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.Effects.EffectBurn;
import MonstreDePoche.models.Effects.EffectParalyze;
import MonstreDePoche.models.Effects.EffectPoison;

public class GroundMonster extends Monster {
    private int nbrTunnel;
    private boolean wasInTunnel;
    public GroundMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.GROUND;
    }

    public GroundMonster(MonsterChoice choice) {
        super(choice);
        this.type = Type.GROUND;
        this.nbrTunnel=0;
        this.wasInTunnel=false;
    }

    @Override
    protected double getAvantage(Monster target, Attack attack){
        if (attack.getType() == Type.GROUND || attack instanceof StruggleAttack){
            if (target instanceof ElectricMonster){
                return 2.0;
            } else if (target instanceof NatureMonster){
                return 0.5;
            }
        }
        return 1.0;
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
                System.out.println(this.name + " stay paralyzed ");
                int RandomParalysis = ThreadLocalRandom.current().nextInt(1, 5);
                if(RandomParalysis !=1){
                    System.out.println(this.name + " cannot move!");
                }
                else{
                    System.out.println(this.name + " Succed attack!");
                    if (this.getHp() > 0) {
                        int nbrTourTunnel;
                        if(this.wasInTunnel==false){
                            nbrTourTunnel = goTunnel();
                            if(nbrTourTunnel !=0){
                                this.nbrTunnel=nbrTourTunnel;
                                this.wasInTunnel=true;
                                this.defense +=20;
                            }
                            int damage;
                            if (attack instanceof StruggleAttack){
                                damage = getDamageStruggle(target, attack);
                            } else {
                                damage = getDamage(target, attack);
                            }
                            attack.useAttack();
                            target.receiveDamage(damage);
                        }else{
                            int damage;
                            if (attack instanceof StruggleAttack){
                                damage = getDamageStruggle(target, attack);
                            } else {
                                damage = getDamage(target, attack);
                            }
                            attack.useAttack();
                            target.receiveDamage(damage);

                            if(this.nbrTunnel!=0){
                                this.nbrTunnel-=1;
                            }
                            else{
                                this.wasInTunnel=false;
                                this.defense -=20;
                            }
                        } 
                    }
                }
            }
        }
        else if(this.currentEffect instanceof EffectBurn){
            int damageBurn = this.attack /10;
            this.receiveDamage(damageBurn);
            System.out.println(this.name + " is affected by burn and loses " + damageBurn + " HP.");

            if (this.getHp() > 0) {
                int nbrTourTunnel;
                if(this.wasInTunnel==false){
                    nbrTourTunnel = goTunnel();
                    if(nbrTourTunnel !=0){
                        this.nbrTunnel=nbrTourTunnel;
                        this.wasInTunnel=true;
                        this.defense +=20;
                    }
                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);
                }else{

                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);

                    if(this.nbrTunnel!=0){
                        this.nbrTunnel-=1;
                    }
                    else{
                        this.wasInTunnel=false;
                        this.defense -=20;
                    }
                }        
            }
        }
        else if(this.currentEffect instanceof EffectPoison){
            int damagePoison = this.attack /10;
            this.receiveDamage(damagePoison);
            System.out.println(this.name + " is affected by poison and loses " + damagePoison + " HP.");
               
            if (this.getHp() > 0) {
                int nbrTourTunnel;
                if(this.wasInTunnel==false){
                    nbrTourTunnel = goTunnel();
                    if(nbrTourTunnel !=0){
                        this.nbrTunnel=nbrTourTunnel;
                        this.wasInTunnel=true;
                        this.defense +=20;
                    }
                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);
                }else{
                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);

                    if(this.nbrTunnel!=0){
                        this.nbrTunnel-=1;
                    }
                    else{
                        this.wasInTunnel=false;
                        this.defense -=20;
                    }
                }  
            }
        }
        else{
            if (this.getHp() > 0) {
                int nbrTourTunnel;
                if(this.wasInTunnel==false){
                    nbrTourTunnel = goTunnel();
                    if(nbrTourTunnel !=0){
                        this.nbrTunnel=nbrTourTunnel;
                        this.wasInTunnel=true;
                        this.defense +=20;
                    }
                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);
                }else{

                    int damage;
                    if (attack instanceof StruggleAttack){
                        damage = getDamageStruggle(target, attack);
                    } else {
                        damage = getDamage(target, attack);
                    }
                    attack.useAttack();
                    target.receiveDamage(damage);

                    if(this.nbrTunnel!=0){
                        this.nbrTunnel-=1;
                        System.out.println("nbrTourTunnel after decrease :"+this.nbrTunnel +1);
                    }
                    else{
                        System.out.println( this.name + "leave the tunnel");
                        this.wasInTunnel=false;
                        this.defense -=20;
                    }
                }
            }
        }        
    }
}
