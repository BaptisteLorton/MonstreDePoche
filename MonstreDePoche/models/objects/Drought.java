package MonstreDePoche.models.objects;

import MonstreDePoche.views.Interface;

public class Drought extends Medicine {

    public Drought (String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof Drought)) {
            return false;
        }
        Drought otherMedicine = (Drought) other;
        return this.name.equals(otherMedicine.name);
    }

    public void useDrought() {
        if (quantity > 0) {
            quantity--;
        }
        Interface.land.flooded = false;
    }
    
}
