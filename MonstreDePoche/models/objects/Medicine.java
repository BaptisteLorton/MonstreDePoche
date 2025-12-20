package MonstreDePoche.models.objects;

public class Medicine extends ObjectToUse {

    public Medicine(String name, int quantity) {
        super(name, quantity);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof Medicine)) {
            return false;
        }
        Medicine otherMedicine = (Medicine) other;
        return this.name.equals(otherMedicine.name);
    }
    
}
