package MonstreDePoche.models.Effects;

public class EffectBurn extends Effect {

    public double burnDamage = 0.10; 

    public EffectBurn(String name, double rateOfEffect) {
        // pour le pourcentage d'effectuer une brulure, on fait la moyenne entre le rateOfEffect passé en paramètre et un nombre aléatoire entre 10% et 90%
        super(name, (rateOfEffect + (0.10 + (Math.random() * (0.90 - 0.10))))/2);
    }

    public EffectBurn() {

    }
    
}
