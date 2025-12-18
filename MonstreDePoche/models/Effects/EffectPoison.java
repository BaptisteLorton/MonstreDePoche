package MonstreDePoche.models.Effects;

public class EffectPoison extends Effect {
    public double poisonDamage = 0.10; 

    public EffectPoison(String name) {
        super(name, 0.33);
    }
    
}
