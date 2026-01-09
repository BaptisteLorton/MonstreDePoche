package MonstreDePoche.models.objects;

import MonstreDePoche.models.monsters.Monster;

public class PoisonMedicine extends Medicine {

    public PoisonMedicine(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof PoisonMedicine)) {
            return false;
        }
        PoisonMedicine otherMedicine = (PoisonMedicine) other;
        return this.name.equals(otherMedicine.name);
    }

    public void usePoisonMedicine(Monster monster) {
        if (quantity > 0) {
            quantity--;
        }
        monster.curePoison();
    }
    
}
