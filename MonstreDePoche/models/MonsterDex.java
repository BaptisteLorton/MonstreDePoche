package MonstreDePoche.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import MonstreDePoche.models.monsters.Monster;

public class MonsterDex {
    Monster[] monsterDex = createMonsterDex("MonstreDePoche/MonstreDePoche/list_monsters/list_monsters.txt");

    Monster[] createMonsterDex(String file){
        Monster[] MonsterDex2 = {};
        
        try {
            String contenu = Files.readString(Paths.get(file));
            System.out.println(contenu);
            String[] monsters = contenu.split("EndMonster");
            for (String monster : monsters){
                String cleanMonster = monster.replace("Monster", "").trim();
                String[] caracteristique = cleanMonster.split("\n");
                String[] car_name = caracteristique[0].split(" ");
                String name = car_name[1];
                System.out.println(name);
                //if (!cleanMonster.isEmpty()) {
                //    System.out.println(cleanMonster);
                //    System.out.println("----");
                //}
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
        return MonsterDex2;
    }
    
}
