package MonstreDePoche.views;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.ArrayList;
import java.util.Scanner;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.Type;
import MonstreDePoche.models.monsters.*;
import MonstreDePoche.controllers.AttacksDex;
import MonstreDePoche.models.objects.*; 

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
                        chosenMonsters[i].setAttacks(AttacksDex.gerenateAttacks(chosenMonsters[i]));
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

    private ArrayList<ObjectToUse> createChoiceObjects() {
        ArrayList<ObjectToUse> objectsAvailable = new ArrayList<>();
        objectsAvailable.add(new Potion("Heal Potion", 1, 20, 0, 0, 0));
        objectsAvailable.add(new Potion("Attack Boost", 1, 0, 5, 0, 0));
        objectsAvailable.add(new Potion("Defense Boost", 1, 0, 0, 5, 0));
        objectsAvailable.add(new Potion("Speed Boost", 1, 0, 0, 0, 5));
        objectsAvailable.add(new Medicine("Antidote", 1));
        objectsAvailable.add(new Medicine("Paralysis Heal", 1));
        // Define Medicine objects
        return objectsAvailable;
    }

    private ArrayList<ObjectToUse> checkMultipleObjects(ObjectToUse[] chosenObjects) {
        ArrayList<ObjectToUse> finalObjects = new ArrayList<>();
        for (ObjectToUse obj : chosenObjects) {
            boolean found = false;
            for (ObjectToUse finalObj : finalObjects) {
                if (obj instanceof Potion && finalObj instanceof Potion) {
                    if (obj.isTheSameObject((Potion) finalObj)) {
                        finalObj.increaseQuantity(1);
                        found = true;
                        break;
                    }
                } else if (obj instanceof Medicine && finalObj instanceof Medicine) {
                    if (obj.isTheSameObject((Medicine) finalObj)) {
                        finalObj.increaseQuantity(1);
                        found = true;
                        break;
                    }
                    finalObj.increaseQuantity(1);
                    found = true;
                    break;
                }
            }
            if (!found) {
                finalObjects.add(obj);
            }
        }
        return finalObjects;
    }

    public void chooseObjectsInterface() {
        System.out.println(color + player.getName() + RESET + ", please choose your objects !");
        String[] inputs = {};
        Scanner scanner = new Scanner(System.in);
        ArrayList<ObjectToUse> objectsAvailable = createChoiceObjects();
        while (inputs.length != 5) {
            for (int i = 0; i < objectsAvailable.size(); i++) {
                System.out.println((i + 1) + ". " + objectsAvailable.get(i).getName());
            }
            System.out.println("\nTo have more information about an object, enter its number.");
            System.out.println("Enter the numbers of the objects you want to choose, separated by spaces");
            System.out.print("\n>");
            String input = scanner.nextLine();
            inputs = input.split(" ");
            if (inputs.length == 1){
                int index = Integer.parseInt(inputs[0]) - 1;
                if (index >= 0 && index < objectsAvailable.size()) {
                    clearConsole();
                    System.out.println(objectsAvailable.get(index).getInformation());
                } else {
                    clearConsole();
                    System.out.println("Invalid object number.\n");
                }
            } else if (inputs.length == 5) {
                ObjectToUse[] chosenObjects = new ObjectToUse[5];
                boolean valid = true;
                for (int i = 0; i < 5; i++) {
                    int index = Integer.parseInt(inputs[i]) - 1;
                    if (index >= 0 && index < objectsAvailable.size()) {
                        chosenObjects[i] = objectsAvailable.get(index);
                    } else {
                        clearConsole();
                        System.out.println("Invalid object number: " + inputs[i] + "\n");
                        valid = false;
                        break;
                    }
                }
                ArrayList<ObjectToUse> finalObjects = checkMultipleObjects(chosenObjects);
                if (valid) {
                    player.setObjects(finalObjects);
                } else {
                    inputs = new String[0];
                }
            } else {
                clearConsole();
                System.out.println("Please enter exactly five numbers. \n");
            }
        }
    }
}
