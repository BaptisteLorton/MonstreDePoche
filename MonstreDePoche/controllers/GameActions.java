package MonstreDePoche.controllers;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.Monster;
import MonstreDePoche.models.actions.Action;
import MonstreDePoche.models.actions.ChangeMonsterAction;
import MonstreDePoche.models.actions.UseObjectAction;
import MonstreDePoche.models.actions.AttackAction;

public class GameActions {

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static Monster[] getAvailableMonsters(Player player) {
        Monster[] monsters = new Monster[player.getMonsters().length-1];
        int idx = 0;
        for (int i = 0; i < player.getMonsters().length; i++) {
            if (player.getMonsters()[i] != player.getActiveMonster()) {
                monsters[idx] = player.getMonsters()[i];
                idx++;
            }
        }
        return monsters;
    }

    public static String checkChangeMonster(Player activePlayer, int monsterIndex) {
        if (monsterIndex == 2) {
            return "";
        }
        Monster[] monsters = getAvailableMonsters(activePlayer);

        if (monsterIndex < 0 || monsterIndex >= monsters.length) {
            return "Invalid monster index!";
        } else if (monsters[monsterIndex].getHp() <= 0) {
            return "You cannot switch to a fainted monster!";
        } else if (monsters.length == 0) {
            return "You have no other monsters to switch to!";
        }
        return "";
    }

    public static Player getFastestPlayerWithFastestMonster(Player player1, Player player2) {
        Monster monster1 = player1.getActiveMonster();
        Monster monster2 = player2.getActiveMonster();
        if (monster1.getSpeed() > monster2.getSpeed()) {
            return player1;
        } else if (monster2.getSpeed() > monster1.getSpeed()) {
            return player2;
        } else {
            return Math.random() < 0.5 ? player1 : player2;
        }
    }

    public static void doBattleActions(Action actionPlayer1, Action actionPlayer2) {
        if (actionPlayer1 instanceof ChangeMonsterAction){
            actionPlayer1.doAction();
            sleep(2000);
        }
        if (actionPlayer2 instanceof ChangeMonsterAction){
            actionPlayer2.doAction();
            sleep(2000);
        }
        if (actionPlayer1 instanceof UseObjectAction){
            actionPlayer1.doAction();
            sleep(2000);
        }
        if (actionPlayer2 instanceof UseObjectAction){
            actionPlayer2.doAction();
            sleep(2000);
        }
        if (actionPlayer1 instanceof AttackAction && actionPlayer2 instanceof AttackAction){
            Player fastestPlayer = getFastestPlayerWithFastestMonster(actionPlayer1.getPlayer(), actionPlayer2.getPlayer());
            if (fastestPlayer == actionPlayer1.getPlayer()) {
                actionPlayer1.doAction();
                sleep(2000);
                if (actionPlayer2.getPlayer().getActiveMonster().getHp() > 0) {
                    actionPlayer2.doAction();
                }
            } else {
                actionPlayer2.doAction();
                sleep(2000);
                if (actionPlayer1.getPlayer().getActiveMonster().getHp() > 0) {
                    actionPlayer1.doAction();
                }
            }
        } else {
            if (actionPlayer1 instanceof AttackAction){
                actionPlayer1.doAction();
            }
            if (actionPlayer2 instanceof AttackAction){
                actionPlayer2.doAction();
            }
        }
    }
}
