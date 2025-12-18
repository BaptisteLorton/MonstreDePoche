package MonstreDePoche.interfaces;

import MonstreDePoche.models.Player;
import static MonstreDePoche.interfaces.ConsoleEffects.*;

public class BattleInterface {
    private String color;
    private final Player activePlayer;
    private final Player otherPlayer;

    public BattleInterface(Player activePlayer, Player otherPlayer, String color) {
        this.activePlayer = activePlayer;
        this.otherPlayer = otherPlayer;
        this.color = color;
    }

    public void battleInterface() {
        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
        System.out.println(showMonsters());
    }
}
