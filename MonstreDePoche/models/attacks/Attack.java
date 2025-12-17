package MonstreDePoche.models.attacks;
import MonstreDePoche.models.Type;

public class Attack {
    public String name;
    public Type type;
    public int power;
    public int nbUse;
    public float fail;

    public Attack(String name, Type type, int power, int nbUse, float fail) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.nbUse = nbUse;
        this.fail = fail;
    }
}
