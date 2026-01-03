package MonstreDePoche.models.actions;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.objects.ObjectToUse;
import MonstreDePoche.models.objects.*;


public class UseObjectAction  extends Action {
    int objectIndex;
    
    public UseObjectAction(Player player, int objectIndex, String message) {
        super(player, message);
        this.objectIndex = objectIndex;
    }

    @Override
    public void doAction(){
        showActionMessage();
        ObjectToUse object = player.getObjects().get(objectIndex);
        if (object instanceof Potion && object.getQuantity() > 0) {
            Potion potion = (Potion) object;
            potion.usePotion();
            potion.applyBonus(player.getActiveMonster());
        }
    }
}
