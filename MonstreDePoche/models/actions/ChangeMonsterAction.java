package MonstreDePoche.models.actions;

import static MonstreDePoche.controllers.GameActions.getAvailableMonsters;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.Monster;

public class ChangeMonsterAction extends Action {
    int monsterIndex;

    public ChangeMonsterAction(Player player, int monsterIndex, String message) {
        super(player, message);
        this.monsterIndex = monsterIndex;
    }

    @Override
    public void doAction(){
        showActionMessage();
        Monster[] monsters = getAvailableMonsters(player);
        Monster monster = monsters[monsterIndex];
        player.setActiveMonster(monster);
    }
}
