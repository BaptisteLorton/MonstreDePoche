package MonstreDePoche.models.objects;

import MonstreDePoche.models.monsters.Monster;

public class Potion extends ObjectToUse {
    int healBoost;
    int attackBoost;
    int defenseBoost;
    int speedBoost;

    public Potion(String name, int quantity, int healBoost, int attackBoost, int defenseBoost, int speedBoost) {
        super(name, quantity);
        this.healBoost = healBoost;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
        this.speedBoost = speedBoost;
    }

    public int getHeal() {
        return healBoost;
    }

    public int getAttackBoost() {
        return attackBoost;
    }

    public int getDefenseBoost() {
        return defenseBoost;
    }

    public int getSpeedBoost() {
        return speedBoost;
    }

    public void usePotion() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void applyBonus(Monster monster) {
        monster.increaseHeal(healBoost);
        monster.increaseAttack(attackBoost);
        monster.increaseAttacks(attackBoost);
        monster.increaseDefense(defenseBoost);
        monster.increaseSpeed(speedBoost);
    }

    @Override
    public boolean isTheSameObject(ObjectToUse other) {
        if (!(other instanceof Potion)) {
            return false;
        }
        Potion otherPotion = (Potion) other;
        return this.healBoost == otherPotion.healBoost &&
               this.attackBoost == otherPotion.attackBoost &&
               this.defenseBoost == otherPotion.defenseBoost &&
               this.speedBoost == otherPotion.speedBoost;
    }

    @Override
    public String getInformation() {
        String info = name + " x" + quantity + " (";
        if (healBoost > 0) {
            info += "Heal: +" + healBoost + " ";
        }
        if (attackBoost > 0) {
            info += "Attack: +" + attackBoost + " ";
        }
        if (defenseBoost > 0) {
            info += "Defense: +" + defenseBoost + " ";
        }
        if (speedBoost > 0) {
            info += "Speed: +" + speedBoost + " ";
        }
        info = info.trim() + ")";
        return info;
    }
}
