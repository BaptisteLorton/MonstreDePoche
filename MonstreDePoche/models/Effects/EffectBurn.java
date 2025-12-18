package MonstreDePoche.models.Effects;

public class EffectBurn extends Effect {

    public double burnDamage = 0.10; 

    public EffectBurn(String name, double rateOfEffect) {
        double rateOfBurn= (rateOfEffect + (0.10 + (Math.random() * (0.90 - 0.10))))/2;
        super(name, rateOfBurn);
    }
    
}
