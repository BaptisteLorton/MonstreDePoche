package MonstreDePoche.models.objects;

public class ObjectToUse {
    String name;
    int quantity;

    public ObjectToUse(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getInformation(){
        return name + " x" + quantity;
    }

    public boolean isTheSameObject(ObjectToUse other){
        return this.name.equals(other.name);
    }
}
