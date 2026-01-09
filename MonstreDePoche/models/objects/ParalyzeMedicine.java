package MonstreDePoche.models.objects;

import MonstreDePoche.models.monsters.Monster;

public class ParalyzeMedicine extends Medicine {

    public ParalyzeMedicine(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof ParalyzeMedicine)) {
            return false;
        }
        ParalyzeMedicine otherMedicine = (ParalyzeMedicine) other;
        return this.name.equals(otherMedicine.name);
    }

    public void useParalyzeMedicine(Monster monster) {
        if (quantity > 0) {
            quantity--;
        }
        monster.cureParalysis();
    }
    
}
