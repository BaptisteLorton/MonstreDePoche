package MonstreDePoche.views;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.Scanner;

import MonstreDePoche.controllers.AudioManager;
import MonstreDePoche.controllers.MonsterDex;
import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.MonsterChoice;
import MonstreDePoche.models.actions.Action;
import MonstreDePoche.controllers.GameActions;

public class Interface {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        clearConsole();

        boolean running = true;
        AudioManager.playMusic("MonstreDePoche/resource/musique_libre_de_droit.wav");

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

            MonsterChoice[] monsterDex = MonsterDex.createMonsterDex("MonstreDePoche/resource/list_monsters.txt");

            ChoiceInterface choiceInterfacePlayer1 = new ChoiceInterface(player1, monsterDex, RED);
            choiceInterfacePlayer1.chooseMonstersInterface();
            clearConsole();
            ChoiceInterface choiceInterfacePlayer2 = new ChoiceInterface(player2, monsterDex, BLUE);
            choiceInterfacePlayer2.chooseMonstersInterface();

            AudioManager.stopMusic();
            AudioManager.playMusic("MonstreDePoche/resource/battle_music.wav");

            BattleInterface battleInterfacePlayer1 = new BattleInterface(player1, player2, RED);
            BattleInterface battleInterfacePlayer2 = new BattleInterface(player2, player1, BLUE);
            while (player1.hasAvailableMonsters() && player2.hasAvailableMonsters()) {
                clearConsole();
                Action actionPlayer1 = battleInterfacePlayer1.battleInterface();
                clearConsole();
                Action actionPlayer2 = battleInterfacePlayer2.battleInterface();
                GameActions.doBattleActions(actionPlayer1, actionPlayer2);
            }

        }
        scanner.close();
    }
}
