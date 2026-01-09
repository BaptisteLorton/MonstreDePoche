package MonstreDePoche.models;

public class Land {
    public boolean flooded;
    public double rateOfFall;
    public int duration;
    public Land(boolean flooded, int rateOfFall, int duration){
        this.flooded = flooded;
        this.rateOfFall = rateOfFall;
        this.duration = duration;
    }
    
}
