package MonstreDePoche.interfaces;

import MonstreDePoche.models.Player;

public class BattleInterface {
    private final Player activePlayer;
    private final Player otherPlayer;

    public BattleInterface(Player activePlayer, Player otherPlayer) {
        this.activePlayer = activePlayer;
        this.otherPlayer = otherPlayer;
    }

    public void battleInterface() {
        System.out.println(activePlayer.getName() + ", it's your turn to play!");
    }
}
