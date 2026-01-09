package MonstreDePoche.models.actions;

import static MonstreDePoche.controllers.GameActions.sleep;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.attacks.StruggleAttack;

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
        sleep(2000);
        if (attackChoice.equals("0")){
            player.getActiveMonster().attack(otherPlayer.getActiveMonster(), new StruggleAttack());
            return;
        }
        player.getActiveMonster().attack(otherPlayer.getActiveMonster(), player.getActiveMonster().getAttacks()[Integer.parseInt(attackChoice)-1]);
    }
}
