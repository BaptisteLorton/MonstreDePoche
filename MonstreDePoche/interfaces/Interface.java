package MonstreDePoche.interfaces;

import java.util.Scanner;

import MonstreDePoche.models.attacks.Attack;
import MonstreDePoche.models.monsters.MonsterFire;

public class Interface {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        Attack[] attacks = new Attack[] {
            new Attack("Flame Burst", MonstreDePoche.models.Type.FIRE, 70, 15, 0.1f),
            new Attack("Fire Spin", MonstreDePoche.models.Type.FIRE, 35, 25, 0.05f)
        };
        MonsterFire monsterFire = new MonsterFire("Flammy", 100, 50, 30, 10, attacks);

        while (running) {
            monsterFire.toString();
            String input = scanner.nextLine();
            System.out.println("You entered: " + input);
        }

        scanner.close();
    }
}
