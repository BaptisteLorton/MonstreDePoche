package MonstreDePoche.models.Effects;

import MonstreDePoche.models.monsters.*;

public class EffectFlood extends Effect {
    int flood;
    int fall;
    Monster monster; 
    public EffectFlood(int flood, int fall, Monster monster){
        this.flood = flood;
        this.fall = fall;   
        this.monster = monster;
    }
    
}
