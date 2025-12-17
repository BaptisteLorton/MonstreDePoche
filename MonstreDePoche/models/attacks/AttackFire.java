package MonstreDePoche.models.attacks;
import MonstreDePoche.models.Type;

public class AttackFire extends Attack {
    public AttackFire(String name, int power, int nbUse, float fail) {
        super(name, Type.FIRE, power, nbUse, fail);
    }
}
