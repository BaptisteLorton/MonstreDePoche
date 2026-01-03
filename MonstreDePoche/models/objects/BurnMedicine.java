package MonstreDePoche.models.objects;

import MonstreDePoche.models.monsters.Monster;

public class BurnMedicine extends Medicine {

    public BurnMedicine(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof BurnMedicine)) {
            return false;
        }
        BurnMedicine otherMedicine = (BurnMedicine) other;
        return this.name.equals(otherMedicine.name);
    }

    public void useBurnMedicine(Monster monster) {
        if (quantity > 0) {
            quantity--;
        }
        monster.cureBurn();
    }
    
}
