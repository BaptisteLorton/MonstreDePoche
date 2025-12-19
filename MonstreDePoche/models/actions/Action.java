package MonstreDePoche.models.actions;

import MonstreDePoche.models.Player;

public class Action {
    Player player;
    String message;

    public Action(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public void doAction() {
    }

    public void showActionMessage() {
        System.out.println(message);
    }
}
