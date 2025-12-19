package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.Type;

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
    public void attack(Monster target, Attack attack) {
        if (this.getHp() > 0) {
            int damage = attack.getPower();
            if (target instanceof GrassMonster){
                damage = attack.getPower()*2;
            } else if (target instanceof WaterMonster){
                damage = attack.getPower()/2;
            }
            target.receiveDamage(damage);
        }
    }
}
