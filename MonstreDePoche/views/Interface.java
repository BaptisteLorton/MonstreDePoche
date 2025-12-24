package MonstreDePoche.views;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.Scanner;

import MonstreDePoche.controllers.AudioManager;
import MonstreDePoche.controllers.MonsterDex;
import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.MonsterChoice;
import MonstreDePoche.models.actions.Action;
import MonstreDePoche.controllers.GameActions;
import MonstreDePoche.models.actions.*;
import static MonstreDePoche.controllers.GameActions.sleep;

public class Interface {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        clearConsole();

        boolean running = true;
        AudioManager.playMusic("MonstreDePoche/MonstreDePoche/resource/musique_libre_de_droit.wav");

        while (running) {
            System.out.println("Welcome to " + ConsoleEffects.CYAN + "PocketMonster" + ConsoleEffects.RESET + " !");
            System.out.println("Please enter the name of the first player: ");
            System.out.print("\n>");
            String input = scanner.nextLine();
            clearConsole();
            Player player1 = new Player(input);
            System.out.println("Please enter the name of the second player: ");
            System.out.print("\n>");
            input = scanner.nextLine();
            clearConsole();
            Player player2 = new Player(input);

            MonsterChoice[] monsterDex = MonsterDex.createMonsterDex("MonstreDePoche/MonstreDePoche/resource/list_monsters.txt");

            ChoiceInterface choiceInterfacePlayer1 = new ChoiceInterface(player1, monsterDex, RED);
            choiceInterfacePlayer1.chooseMonstersInterface();
            clearConsole();
            choiceInterfacePlayer1.chooseObjectsInterface();
            clearConsole();
            ChoiceInterface choiceInterfacePlayer2 = new ChoiceInterface(player2, monsterDex, BLUE);
            choiceInterfacePlayer2.chooseMonstersInterface();
            clearConsole();
            choiceInterfacePlayer2.chooseObjectsInterface();

            AudioManager.stopMusic();
            AudioManager.playMusic("MonstreDePoche/MonstreDePoche/resource/battle_music.wav");

            BattleInterface battleInterfacePlayer1 = new BattleInterface(player1, player2, RED);
            BattleInterface battleInterfacePlayer2 = new BattleInterface(player2, player1, BLUE);
            Action actionPlayer1 = null;
            Action actionPlayer2 = null;
            running = true;
            while (player1.hasAvailableMonsters() && player2.hasAvailableMonsters() && running) {
                if (player2.getActiveMonster().getHp() <= 0) {
                    clearConsole();
                    actionPlayer2 = battleInterfacePlayer2.battleInterface();
                    clearConsole();
                    actionPlayer1 = battleInterfacePlayer1.battleInterface();
                } else {
                    clearConsole();
                    actionPlayer1 = battleInterfacePlayer1.battleInterface();
                    clearConsole();
                    actionPlayer2 = battleInterfacePlayer2.battleInterface();
                }
                
                if (actionPlayer1 instanceof SurrenderAction || actionPlayer2 instanceof SurrenderAction) {
                    actionPlayer1.doAction();
                    sleep(2000);
                    actionPlayer2.doAction();
                    sleep(2000);
                    running = false;
                    break;
                }
                GameActions.doBattleActions(actionPlayer1, actionPlayer2);
            }
            if (actionPlayer1 instanceof SurrenderAction && actionPlayer2 instanceof SurrenderAction){
                System.out.println("\nBoth players have surrendered! It's a draw!");
            } else if (player1.hasAvailableMonsters() || actionPlayer2 instanceof SurrenderAction) {
                System.out.println(RED + player1.getName() + RESET + " wins the battle!");
            } else {
                System.out.println(BLUE + player2.getName() + RESET + " wins the battle!");
            }
            AudioManager.stopMusic();
        }
        scanner.close();
    }
}
