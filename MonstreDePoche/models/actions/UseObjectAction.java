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
        } else {
            if (object instanceof ParalyzeMedicine && object.getQuantity() > 0) {
                ParalyzeMedicine paralyzeMedicine = (ParalyzeMedicine) object;
                paralyzeMedicine.useParalyzeMedicine(player.getActiveMonster());
            } else if (object instanceof BurnMedicine && object.getQuantity() > 0) {
                BurnMedicine burnMedicine = (BurnMedicine) object;
                burnMedicine.useBurnMedicine(player.getActiveMonster());
            } else if (object instanceof PoisonMedicine && object.getQuantity() > 0) {
                PoisonMedicine poisonMedicine = (PoisonMedicine) object;
                poisonMedicine.usePoisonMedicine(player.getActiveMonster());
            } else if( object instanceof Drought && object.getQuantity() > 0) {
                Drought drought = (Drought) object;
                drought.useDrought();
            }
        }
    }
}
