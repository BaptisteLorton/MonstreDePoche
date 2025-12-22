package MonstreDePoche.models.Effects;

public class EffectParalyze extends Effect {
    public int paralyseDuration = 6;

    public EffectParalyze(String name) {
        super( name, 0.25);
    }

    public EffectParalyze() {
        paralyseDuration = 6;

    }
    
}
