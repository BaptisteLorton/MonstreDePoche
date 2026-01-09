package MonstreDePoche.models.monsters;
import MonstreDePoche.models.Type;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.attacks.StruggleAttack;

public class NatureMonster extends Monster {
    public NatureMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
    }

    public NatureMonster(MonsterChoice choice) {
        super(choice);
    }

    @Override
    protected double getAvantage(Monster target, Attack attack){
        if (attack.getType() == Type.NATURE || attack instanceof StruggleAttack){
            if (target instanceof GroundMonster){
                return 2.0;
            } else if (target instanceof FireMonster){
                return 0.5;
            }
        }
        return 1.0;
    }

    @Override
    public void attack(Monster target, Attack attack) {
        if (this.getHp() > 0) {
            int damage = attack.getPower();
            if (target instanceof GroundMonster){
                damage = attack.getPower()*2;
            } else if (target instanceof FireMonster){
                damage = attack.getPower()/2;
            }
            attack.useAttack();
            target.receiveDamage(damage);
        }
    }
}
