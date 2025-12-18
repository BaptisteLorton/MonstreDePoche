package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.Type;

public class GrassMonster extends Monster {
    public GrassMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.GRASS;
    }

    public void attack(Monster target, Attack attack) {
        int damage = attack.getPower();
        if (target instanceof GroundMonster){
            damage = attack.getPower()*2;
        } else if (target instanceof FireMonster){
            damage = attack.getPower()/2;
        }
        target.receiveDamage(damage);
    }
}
