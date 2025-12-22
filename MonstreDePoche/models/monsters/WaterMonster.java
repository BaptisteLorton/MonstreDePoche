package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.Type;

public class WaterMonster extends Monster {
    public WaterMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.WATER;
    }

    public WaterMonster(MonsterChoice choice) {
        super(choice);
        this.type = Type.WATER;
    }

    @Override
    public void attack(Monster target, Attack attack) {
        if (this.getHp() > 0) {
            int damage = attack.getPower();
            if (target instanceof FireMonster){
                damage = attack.getPower()*2;
            } else if (target instanceof ElectricMonster){
                damage = attack.getPower()/2;
            }
            target.receiveDamage(damage);
        }
    }
}
