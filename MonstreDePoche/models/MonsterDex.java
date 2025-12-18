package MonstreDePoche.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import MonstreDePoche.models.monsters.Monster;

public class MonsterDex {
    
    // Note : Vérifiez bien votre chemin d'accès selon la structure de votre projet.
    //private MonsterChoice[] monsterDex = createMonsterDex("MonstreDePoche/MonstreDePoche/list_monsters/list_monsters.txt");

    public static MonsterChoice[] createMonsterDex(String file) {
        //List<MonsterChoice> tempDexList = new ArrayList<>();
        MonsterChoice[] tempDexList = {};

        try {
            String contenu = Files.readString(Paths.get(file));
            
            // Nettoyage global (balises éventuelles)
            contenu = contenu.replaceAll("<[^>]*>", ""); 

            // Découpage par bloc de monstre
            String[] monstersRaw = contenu.split("EndMonster");

            for (String rawBlock : monstersRaw) {
                String cleanBlock = rawBlock.replace("Monster", "").trim();
                if (cleanBlock.isEmpty()) continue;

                String[] lines = cleanBlock.split("\\R");
                
                // Variables temporaires pour le monstre
                String name = "";
                String type = "";
                
                // Stats (Min / Max)
                int hpMin = 0, hpMax = 0;
                int atkMin = 0, atkMax = 0;
                int defMin = 0, defMax = 0;
                int spdMin = 0, spdMax = 0;

                // Boucle avec index 'i' pour pouvoir sauter des lignes si besoin
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i].trim();
                    if (line.isEmpty()) continue;

                    // 1. Gestion du NOM
                    if (line.startsWith("Name")) {
                        name = line.replace("Name", "").trim(); 
                    } 
                    // 2. Gestion du TYPE
                    else if (line.startsWith("Type")) {
                        type = line.replace("Type", "").trim();
                    } 
                    // 3. Gestion des STATS (HP, Attack, Defense, Speed)
                    // On utilise une logique commune pour gérer le cas où la valeur est à la ligne suivante
                    else if (line.startsWith("HP") || line.startsWith("Attack") || 
                             line.startsWith("Defense") || line.startsWith("Speed")) {
                        
                        // On récupère le mot clé (ex: "HP")
                        String key = line.split("\\s+")[0];     
                                          
                        // On tente de récupérer ce qu'il y a après le mot clé
                        String valueRaw = line.substring(key.length()).trim();

                        // SI la ligne est vide après le mot clé (ex: cas Onix ou Tortank), on lit la ligne SUIVANTE
                        if (valueRaw.isEmpty() && i + 1 < lines.length) {
                            i++; // On avance l'index principal pour ne pas relire cette ligne au prochain tour
                            valueRaw = lines[i].trim();
                        }

                        // Analyse des deux nombres (Min Max)
                        // On sépare par les espaces
                        String[] values = valueRaw.split("\\s+");
                        if (values.length >= 2) {
                            int val1 = Integer.parseInt(values[0]);
                            int val2 = Integer.parseInt(values[1]);

                            if (key.equals("HP")) {
                                hpMin = val1; hpMax = val2;
                            } else if (key.equals("Attack")) {
                                atkMin = val1; atkMax = val2;
                            } else if (key.equals("Defense")) {
                                defMin = val1; defMax = val2;
                            } else if (key.equals("Speed")) {
                                spdMin = val1; spdMax = val2;
                            }
                        }
                    }
                }

                if (!name.isEmpty()) {
                    System.out.println("------------------------------------------------");
                    System.out.println("Monstre : " + name + " (" + type + ")");
                    System.out.println("HP: " + hpMin + "-" + hpMax + " | Atk: " + atkMin + "-" + atkMax);
                    System.out.println("Def: " + defMin + "-" + defMax + " | Spd: " + spdMin + "-" + spdMax);
                    
                    monsterDex.add(new MonsterChoice(name, type, hpMax, hpMin, atkMax, atkMin, defMax, defMin, spdMax, spdMin));

                    // Création de l'objet (Vérifiez votre constructeur Monster !)
                    // Monster m = new Monster(name, type, hpMin, hpMax, atkMin, atkMax, defMin, defMax, spdMin, spdMax);
                    // tempDexList.add(m);
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur de parsing : " + e.getMessage());
            e.printStackTrace();
        }

        return tempDexList;
    }
}