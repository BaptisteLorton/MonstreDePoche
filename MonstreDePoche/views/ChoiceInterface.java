package MonstreDePoche.views;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.Scanner;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.Type;
import MonstreDePoche.models.monsters.ElectricMonster;
import MonstreDePoche.models.monsters.FireMonster;
import MonstreDePoche.models.monsters.GrassMonster;
import MonstreDePoche.models.monsters.GroundMonster;
import MonstreDePoche.models.monsters.InsectMonster;
import MonstreDePoche.models.monsters.Monster;
import MonstreDePoche.models.monsters.MonsterChoice;
import MonstreDePoche.models.monsters.WaterMonster;

public class ChoiceInterface {

    Player player;
    String color;
    MonsterChoice[] monstersDex;

    public ChoiceInterface(Player player, MonsterChoice[] monstersDex, String color) {
        this.player = player;
        this.monstersDex = monstersDex;
        this.color = color;
    }

    private Monster createMonster(MonsterChoice choice) {
        switch (choice.getType()) {
            case Type.FIRE:
                return new FireMonster(choice);
            case Type.WATER:
                return new WaterMonster(choice);
            case Type.GRASS:
                return new GrassMonster(choice);
            case Type.INSECT:
                return new InsectMonster(choice);
            case Type.ELECTRIC:
                return new ElectricMonster(choice);
            case Type.GROUND:
                return new GroundMonster(choice);
            default:
                return null;
        }
    }

    public void chooseMonstersInterface() {
        System.out.println(color + player.getName() + RESET + ", please choose your monsters !");
        String[] inputs = {};
        Scanner scanner = new Scanner(System.in);
        while (inputs.length != 3) {
            for (int i = 0; i < monstersDex.length; i++) {
                System.out.println((i + 1) + ". " + monstersDex[i].getSmallInformation());
            }
            System.out.println("\nTo have more information about a monster, enter its number.");
            System.out.println("Enter the numbers of the monsters you want to choose, separated by spaces");
            System.out.print("\n>");
            String input = scanner.nextLine();
            inputs = input.split(" ");
            if (inputs.length == 1){
                int index = Integer.parseInt(inputs[0]) - 1;
                if (index >= 0 && index < monstersDex.length) {
                    clearConsole();
                    System.out.println(monstersDex[index].getFullInformation());
                } else {
                    clearConsole();
                    System.out.println("Invalid monster number.");
                }
            } else if (inputs.length == 3) {
                Monster[] chosenMonsters = new Monster[3];
                boolean valid = true;
                for (int i = 0; i < 3; i++) {
                    int index = Integer.parseInt(inputs[i]) - 1;
                    if (index >= 0 && index < monstersDex.length) {
                        chosenMonsters[i] = createMonster(monstersDex[index]);
                        //chosenMonsters[i].setAttacks(gerenateAttacks(chosenMonsters[i]));
                    } else {
                        clearConsole();
                        System.out.println("Invalid monster number: " + inputs[i]);
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    player.setMonsters(chosenMonsters);
                } else {
                    inputs = new String[0];
                }
            } else {
                clearConsole();
                System.out.println("Please enter exactly three numbers.");
            }
            System.out.println("\n---------------------------\n");
        }
    }
}
