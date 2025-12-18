package MonstreDePoche.interfaces;

import java.util.Scanner;

import MonstreDePoche.models.MonsterDex;
import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.Monster;
import MonstreDePoche.models.monsters.MonsterChoice;

public class Interface {

    private void chooseMonstersInterface(Player player, MonsterChoice[] monstersDex) {
        System.out.println(player.getName() + ", please choose your monsters !");
        for (int i = 0; i < monstersDex.length; i++) {
            System.out.println((i + 1) + ". " + monstersDex[i].getSmallInformation());
        }
        String[] inputs = {};
        Scanner scanner = new Scanner(System.in);
        while (inputs.length != 3) {
            System.out.println("To have more information about a monster, enter its number.");
            System.out.println("Enter the numbers of the monsters you want to choose, separated by spaces");
            String input = scanner.nextLine();
            inputs = input.split(" ");
            if (inputs.length == 1){
                int index = Integer.parseInt(inputs[0]) - 1;
                if (index >= 0 && index < monstersDex.length) {
                    System.out.println(monstersDex[index].getFullInformation());
                } else {
                    System.out.println("Invalid monster number.");
                }
            } else if (inputs.length == 3) {
                Monster[] chosenMonsters = new Monster[3];
                boolean valid = true;
                for (int i = 0; i < 3; i++) {
                    int index = Integer.parseInt(inputs[i]) - 1;
                    if (index >= 0 && index < monstersDex.length) {
                        chosenMonsters[i] = new Monster(monstersDex[index]);
                    } else {
                        System.out.println("Invalid monster number: " + inputs[i]);
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    player.setMonsters(chosenMonsters);
                    System.out.println("You have chosen:");
                    for (Monster m : chosenMonsters) {
                        System.out.println("- " + m.getName());
                    }
                } else {
                    inputs = new String[0];
                }
            } else {
                System.out.println("Please enter exactly three numbers.");
            }
        }
        scanner.close();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("Welcome to PocketMonster !");
            System.out.println("Please enter the name of the first player: ");
            String input = scanner.nextLine();
            System.out.println("Hello " + input + "!");
            Player player1 = new Player(input);
            System.out.println("Please enter the name of the second player: ");
            input = scanner.nextLine();
            System.out.println("Hello " + input + "!");
            Player player2 = new Player(input);

            MonsterChoice[] monsterDex = MonsterDex.createMonsterDex("MonstreDePoche/MonstreDePoche/list_monsters/list_monsters.txt");

            chooseMonstersInterface(player1, monsterDex);
            chooseMonstersInterface(player2, monsterDex);
        }

        scanner.close();
    }
}
