package MonstreDePoche.controllers;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.Monster;

public class GameActions {

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

    public static void doAttack(Player activePlayer, Player otherPlayer, String input) {
        activePlayer.getActiveMonster().attack(otherPlayer.getActiveMonster(), activePlayer.getActiveMonster().getAttacks()[Integer.parseInt(input)-1]);
    }

    public static void changeActiveMonster(Player player, int monsterIndex) {
        Monster[] monsters = getAvailableMonsters(player);
        Monster monster = monsters[monsterIndex];
        player.setActiveMonster(monster);
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
}
