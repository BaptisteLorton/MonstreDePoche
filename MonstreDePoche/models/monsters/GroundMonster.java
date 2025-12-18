package MonstreDePoche.models.monsters;
import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.Type;

public class GroundMonster extends Monster {
    public GroundMonster(String name, int hp, int attack, int defense, int speed, Attack[] attacks) {
        super(name, hp, attack, defense, speed, attacks);
        this.type = Type.GROUND;
    }

    public GroundMonster(MonsterChoice choice) {
        super(choice);
        this.type = Type.GROUND;
    }

    public void attack(Monster target, Attack attack) {
        int damage = attack.getPower();
        if (target instanceof ElectricMonster){
            damage = attack.getPower()*2;
        } else if (target instanceof GrassMonster){
            damage = attack.getPower()/2;
        }
        target.receiveDamage(damage);
    }
}
