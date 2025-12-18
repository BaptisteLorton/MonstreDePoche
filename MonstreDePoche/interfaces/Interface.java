package MonstreDePoche.interfaces;

import java.util.Scanner;

import MonstreDePoche.models.MonsterDex;
import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.MonsterChoice;

public class Interface {

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            } else {
                new ProcessBuilder("clear")
                    .inheritIO()
                    .start()
                    .waitFor();
            }
        } catch (Exception e) {
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        clearConsole();

        boolean running = true;

        while (running) {
            System.out.println("Welcome to PocketMonster !");
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

            MonsterChoice[] monsterDex = MonsterDex.createMonsterDex("MonstreDePoche/list_monsters/list_monsters.txt");

            ChoiceInterface choiceInterfacePlayer1 = new ChoiceInterface(player1, monsterDex);
            choiceInterfacePlayer1.chooseMonstersInterface();
            clearConsole();
            ChoiceInterface choiceInterfacePlayer2 = new ChoiceInterface(player2, monsterDex);
            choiceInterfacePlayer2.chooseMonstersInterface();


            BattleInterface battleInterfacePlayer1 = new BattleInterface(player1, player2);
            BattleInterface battleInterfacePlayer2 = new BattleInterface(player2, player1);
            Player activePlayer = player1.getMonsters()[0].getSpeed() >= player2.getMonsters()[0].getSpeed() ? player1 : player2;
            while (activePlayer.canPlay()) {
                clearConsole();
                if (activePlayer == player1) {
                    battleInterfacePlayer1.battleInterface();
                    activePlayer = player2;
                } else {
                    battleInterfacePlayer2.battleInterface();
                    activePlayer = player1;
                }
            }

        }
        scanner.close();
    }
}
