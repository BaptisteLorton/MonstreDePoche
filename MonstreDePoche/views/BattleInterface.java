package MonstreDePoche.views;

import static MonstreDePoche.views.ConsoleEffects.*;

import java.util.Scanner;

import MonstreDePoche.models.Player;
import MonstreDePoche.models.monsters.Monster;

public class BattleInterface {
    private String color;
    private final Player activePlayer;
    private final Player otherPlayer;

    public BattleInterface(Player activePlayer, Player otherPlayer, String color) {
        this.activePlayer = activePlayer;
        this.otherPlayer = otherPlayer;
        this.color = color;
    }

    private String showHp(Monster monster) {
        double hpRatio = (double) monster.getHp() / monster.getHpMax();
        int totalBars = 20;
        int filledBars = (int) (hpRatio * totalBars);
        StringBuilder hpBar = new StringBuilder("");
        for (int i = 0; i < totalBars; i++) {
            if (i == 0 && filledBars == 0) {
                hpBar.append("[");
            } else if (i < filledBars) {
                hpBar.append("█");
            } else {
                System.out.println(i + " " + filledBars + " " + totalBars);
                if (i == totalBars - 1 && filledBars != totalBars) {
                    hpBar.append("]");
                } else {
                    hpBar.append(" ");
                }
            }
        }
        hpBar.append(" ");
        hpBar.append(monster.getHp()).append("/").append(monster.getHpMax()).append(" HP");
        return hpBar.toString();
    }

    private String showMonsters() {
        String string = "";
        String otherInfo = otherPlayer.getActiveMonster().getColor() + otherPlayer.getActiveMonster().getName() + RESET;
        otherInfo += "\n" + showHp(otherPlayer.getActiveMonster());
        String activeInfo = activePlayer.getActiveMonster().getColor() + activePlayer.getActiveMonster().getName() + RESET;
        activeInfo += "\n" + showHp(activePlayer.getActiveMonster());
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
    
    private String showMenu(int page){
        String string = "\nWhat do you want to do?\n";
        if(page == 0){
            string += "1 - Attack\n";
            string += "2 - Use Item\n";
            string += "3 - Switch Monster\n";
            string += "4 - Surrender\n";
        } else if(page == 1){
            string += "Choose an attack:\n";
            string += "1 - " + activePlayer.getActiveMonster().getAttacks()[0] + "\n";
            string += "2 - " + activePlayer.getActiveMonster().getAttacks()[1] + "\n";
            string += "3 - " + activePlayer.getActiveMonster().getAttacks()[2] + "\n";
            string += "4 - " + activePlayer.getActiveMonster().getAttacks()[3] + "\n";
            string += "5 - Back to main menu\n";
        }
        return string;
    }

    public void battleInterface() {
        Scanner scanner = new Scanner(System.in);
        
        activePlayer.getActiveMonster().receiveDamage(100);

        System.out.println(color + activePlayer.getName() + RESET + ", it's your turn to play!");
        System.out.println(showMonsters());
        System.out.println(showMenu(0));
        boolean running = true;
        while (running) {
            System.out.print(">");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    clearConsole();
                    System.out.println(showMonsters());
                    System.out.println(showMenu(1));
                default:
                    System.out.println("Invalid input. Please enter a number corresponding to your choice.");
                    break;
            }
        }
    }
}
