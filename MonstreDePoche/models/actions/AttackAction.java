package MonstreDePoche.models.actions;

import MonstreDePoche.models.Player;

public class AttackAction extends Action {
    String attackChoice;
    Player otherPlayer;
    
    public AttackAction(Player player, Player otherPlayer, String message, String attackChoice) {
        super(player, message);
        this.attackChoice = attackChoice;
        this.otherPlayer = otherPlayer;
    }

    @Override
    public void doAction(){
        showActionMessage();
        if (attackChoice.equals("0")){
            player.getActiveMonster().struggle(otherPlayer.getActiveMonster());
            return;
        }
        player.getActiveMonster().attack(otherPlayer.getActiveMonster(), player.getActiveMonster().getAttacks()[Integer.parseInt(attackChoice)-1]);
    }
}
