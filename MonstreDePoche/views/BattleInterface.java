package MonstreDePoche.views;

import java.util.Scanner;

import static MonstreDePoche.views.ConsoleEffects.*;
import MonstreDePoche.models.Player;
import MonstreDePoche.models.actions.*;
import MonstreDePoche.models.monsters.Monster;
import static MonstreDePoche.controllers.GameActions.*;
import MonstreDePoche.models.objects.ObjectToUse;

public class BattleInterface {
    private String color;
    private final Player activePlayer;
    private final Player otherPlayer;
    

    public BattleInterface(Player activePlayer, Player otherPlayer, String color) {
        this.activePlayer = activePlayer;
        this.otherPlayer = otherPlayer;
        this.color = color;
    }
    
    private String showMenu(int page){
        String string = "\nWhat do you want to do?\n";
        if(page == 0){
            string += "1 - Attack\n";
            string += "2 - Use Item\n";
            string += "3 - Switch Monster\n";
            string += "4 - Surrender\n";
        } else if(page == 1){
            string += "Choose an attack:\n";
            string += "1 - " + activePlayer.getActiveMonster().getAttacks()[0].getName() + " (" + activePlayer.getActiveMonster().getAttacks()[0].getNumberOfAttacks() + ")\n";
            string += "2 - " + activePlayer.getActiveMonster().getAttacks()[1].getName() + " (" + activePlayer.getActiveMonster().getAttacks()[1].getNumberOfAttacks() + ")\n";
            string += "3 - " + activePlayer.getActiveMonster().getAttacks()[2].getName() + " (" + activePlayer.getActiveMonster().getAttacks()[2].getNumberOfAttacks() + ")\n";
            string += "4 - " + activePlayer.getActiveMonster().getAttacks()[3].getName() + " (" + activePlayer.getActiveMonster().getAttacks()[3].getNumberOfAttacks() + ")\n";
            string += "5 - Back to main menu\n";
        } else if (page == 2){
            string += "Choose an item:\n";
            ObjectToUse[] objects = activePlayer.getObjects().toArray(new ObjectToUse[0]);
            for(int i = 0; i < objects.length; i++){
                if (objects[i].getQuantity() <= 0) {
                    continue;
                }
                string += (i + 1) + " - " + objects[i].getInformation() + "\n";
            }
            string += (objects.length + 1) + " - Back to main menu\n";
        } else if(page == 3){
            string += "Choose a monster to switch to:\n";
            Monster[] monsters = getAvailableMonsters(activePlayer);
            for(int i = 0; i < monsters.length; i++){
                if (monsters[i] == activePlayer.getActiveMonster()) {
                    continue;
                } else {
                    if (monsters[i].getHp() <= 0) {
                        string += (i + 1) + " - " + GRAY + monsters[i].getName() + RESET + " (" + monsters[i].getHp() + "/" + monsters[i].getHpMax() + " HP)\n";
                    } else {
                        string += (i + 1) + " - " + monsters[i].getColor() + monsters[i].getName() + RESET + " (" + monsters[i].getHp() + "/" + monsters[i].getHpMax() + " HP)\n";
                    }
                }
            }
            if (otherPlayer.getActiveMonster().getHp() <= 0) {
                string += "3 - Keep current monster";
            } else if (activePlayer.getActiveMonster().getHp() > 0) {
                string += "3 - Back to main menu\n";
            }
        }
        return string;
    }

    private String showHp(Monster monster) {
        double hpRatio = (double) monster.getHp() / monster.getHpMax();
        int totalBars = 20;
        int filledBars = (int) (hpRatio * totalBars);
        String color = "";
        if (hpRatio >= 0.5) {
            color = GREEN;
        } else if (hpRatio >= 0.2) {
            color = YELLOW;
        } else {
            color = RED;
        }
        StringBuilder hpBar = new StringBuilder("");
        hpBar.append(color);
        for (int i = 0; i < totalBars; i++) {
            if (i < filledBars) {
                hpBar.append("█");
            } else {
                hpBar.append(GRAY);
                hpBar.append("░");
            }
        }
        hpBar.append(RESET);
        hpBar.append(" ");
        hpBar.append(monster.getHp()).append("/").append(monster.getHpMax()).append(" HP");
        return hpBar.toString();
    }

    private String showMonsters() {
        String string = "";
        String otherInfo = otherPlayer.getActiveMonster().getColor() + otherPlayer.getActiveMonster().getName() + RESET;
        otherInfo += "\n" + showHp(otherPlayer.getActiveMonster());
        String activeInfo = activePlayer.getActiveMonster().getColor() + activePlayer.getActiveMonster().getName() + RESET;
        activeInfo += "\n                     " + showHp(activePlayer.getActiveMonster());
        string += "\n" + otherInfo + "\n";
        string += """

                                           _,--''--._        
                                          /  _    _  \\       
                                       _  \\(o )  (o )/  _   
                                      (_\\  '.__  __.' /_ )  
                                         `-.   ''    .-'     
                                            |        |        
                                            \\       /        
                                             '.____.'

        
                 _,--''--._        
                /          \\       
             _  \\          /  _   
           (_\\              /_ )  
              `-.          .-'     
                 |        |        
                 \\       /        
                 '.____.'

           """;
        string += "                     " + activeInfo + "\n";
        return string;
    }

    public Action battleInterface() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
            if(Interface.land.flooded == true){
                System.out.println(YELLOW + "The land is currently flooded ! " + RESET);
            }
            System.out.println(showMonsters());
            System.out.println(showMenu(0));
            String input;
            if (activePlayer.getActiveMonster().getHp() <= 0 || otherPlayer.getActiveMonster().getHp() <= 0) {
                input = "3";
            } else {
                System.out.print(">");
                input = scanner.nextLine();
            }
            switch (input) {
                case "1":
                    clearConsole();
                    if (!activePlayer.getActiveMonster().hasAttacksLeft()){
                        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
                        System.out.println(showMonsters());
                        return new AttackAction(activePlayer, otherPlayer, "\nNo attacks left! " + activePlayer.getActiveMonster().getColor()  + activePlayer.getActiveMonster().getName() + RESET + " uses Struggle!", "0");
                    }
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
                        if(Interface.land.flooded == true){
                            System.out.println(YELLOW + "The land is currently flooded ! " + RESET);
                        }
                        System.out.println(showMonsters());
                        System.out.println(showMenu(1));
                        System.out.print(">");
                        input = scanner.nextLine();
                        if (input.equals("5")) {
                            clearConsole();
                            break;
                        } else {
                            String message = "";
                            if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > 5) {
                                clearConsole();
                                System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                            } else {
                                message = "\n" + activePlayer.getActiveMonster().getColor() + activePlayer.getActiveMonster().getName() + RESET + " used " + activePlayer.getActiveMonster().getAttacks()[Integer.parseInt(input)-1].getName() + "!";
                                return new AttackAction(activePlayer, otherPlayer, message, input);
                            }
                        }
                    }
                    clearConsole();
                    break;
                case "2":
                    clearConsole();
                    boolean validUseItem = false;
                    while (!validUseItem) {
                        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
                        if(Interface.land.flooded == true){
                            System.out.println(YELLOW + "The land is currently flooded ! " + RESET);
                        }
                        System.out.println(showMonsters());
                        System.out.println(showMenu(2));
                        System.out.print(">");
                        input = scanner.nextLine();
                        if (Integer.parseInt(input) == activePlayer.getObjects().size() + 1) {
                            clearConsole();
                            break;
                        }
                        if (Integer.parseInt(input) < 1 || Integer.parseInt(input) > activePlayer.getObjects().size() + 1) {
                            clearConsole();
                            System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                        } else {
                            String message = "\n" + activePlayer.getName() + " used " + activePlayer.getObjects().get(Integer.parseInt(input)-1).getName() + "!";;
                            validUseItem = true;
                            return new UseObjectAction(activePlayer, Integer.parseInt(input)-1, message);
                        }
                    }
                    break;
                case "3":
                    clearConsole();
                    if (activePlayer.getActiveMonster().getHp() <= 0) {
                        System.out.println("Your active monster has fainted! You must switch to another monster.\n");
                    } else if (otherPlayer.getActiveMonster().getHp() <= 0) {
                        System.out.println("The opponent's active monster has fainted! You have the opportunity to switch to another monster.\n");
                    }
                    String check = "start";
                    while (check != "") {
                        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
                        if(Interface.land.flooded == true){
                            System.out.println(YELLOW + "The land is currently flooded ! " + RESET);
                        }
                        System.out.println(showMonsters());
                        System.out.println(showMenu(3));
                        System.out.print(">");
                        input = scanner.nextLine();
                        check = checkChangeMonster(activePlayer, Integer.parseInt(input)-1);
                        if (check == "") {
                            if (input.equals("3") && activePlayer.getActiveMonster().getHp() > 0) {
                                if (otherPlayer.getActiveMonster().getHp() <= 0) {
                                    String message = "\n" + color + activePlayer.getName() + RESET + " kept " + activePlayer.getActiveMonster().getName() + " as active monster!";
                                    return new Action(activePlayer, message);
                                }
                                clearConsole();
                                break;
                            }
                            String message = "\n" + color + activePlayer.getName() + RESET + " switched to " + getAvailableMonsters(activePlayer)[Integer.parseInt(input)-1].getName() + "!";
                            return new ChangeMonsterAction(activePlayer, Integer.parseInt(input)-1, message);
                        } else {
                            System.out.println("\n" + check);
                        }
                        sleep(2000);
                        clearConsole();
                    }
                    break;
                case "4":
                    String message = "\n" + color + activePlayer.getName() + RESET + " has surrendered!";
                    return new SurrenderAction(activePlayer, message);
                default:
                    System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                    break;
            }
        }
    }
}
