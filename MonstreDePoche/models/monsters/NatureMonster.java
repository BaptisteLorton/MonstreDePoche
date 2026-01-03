package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;

public class NatureMonster extends Monster {
    public NatureMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
    }

    public NatureMonster(MonsterChoice choice) {
        super(choice);
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
