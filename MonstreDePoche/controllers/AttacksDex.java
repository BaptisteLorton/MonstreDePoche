package MonstreDePoche.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import MonstreDePoche.models.Type;
import MonstreDePoche.models.attacks.*;
import MonstreDePoche.models.monsters.Monster;
import MonstreDePoche.models.monsters.MonsterChoice;

public class AttacksDex { 

    public static Attack[] gerenateAttacks(Monster monster) {
        Attack[] attacks = new Attack[4];

        ArrayList<Attack> attacks_fire = new ArrayList<>();
        ArrayList<Attack> attacks_water = new ArrayList<>();
        ArrayList<Attack> attacks_ground = new ArrayList<>();
        ArrayList<Attack> attacks_nature = new ArrayList<>();
        ArrayList<Attack> attacks_normal = new ArrayList<>();
        ArrayList<Attack> attacks_electric = new ArrayList<>();

        Random random = new Random();

        
        try {
            String contenu = Files.readString(Paths.get("MonstreDePoche/MonstreDePoche/resource/list_attaque.txt"));
            contenu = contenu.replaceAll("<[^>]*>", "");

            String[] attacksRaw = contenu.split("EndAttack");

            for (String rawBlock : attacksRaw) {
                //System.out.println(rawBlock.replace("Attack", "").trim());

                String cleanBlock = rawBlock.replace("Attack", "").trim();
                if (cleanBlock.isEmpty()) continue;
                String[] lines = cleanBlock.split("\n");

                String name ="";
                Type type= null;
                int power =0;
                int nbUse =0;
                float fail=0.0f;
                for (String line : lines){
                    line = line.trim();
                    if(line.startsWith("Name")){
                        String[] temp = line.split(" ");
                        //name = temp[1];
                        name = Arrays.stream(temp).skip(1).collect(Collectors.joining(" "));
                    }
                    else if(line.startsWith("Type")){
                        String[] temp = line.split(" ");
                        type = Type.valueOf(temp[1].toUpperCase());
                    }
                    else if(line.startsWith("Power")){
                        String[] temp = line.split(" ");
                        power = Integer.parseInt(temp[1]);
                    }
                    else if(line.startsWith("NbUse")){
                        String[] temp = line.split(" ");
                        nbUse = Integer.parseInt(temp[1]);
                    }
                    else if(line.startsWith("Fail")){
                        String[] temp = line.split(" ");
                        fail = Float.parseFloat(temp[1]);
                    }
                }
                if(type==Type.FIRE){
                    attacks_fire.add(new Attack(name, type, power, nbUse, fail));  
                }
                else if(type==Type.WATER){
                    attacks_water.add(new Attack(name, type, power, nbUse, fail));
                }
                else if(type==Type.GROUND){
                    attacks_ground.add(new Attack(name, type, power, nbUse, fail));
                }
                else if(type==Type.NATURE){
                    attacks_nature.add(new Attack(name, type, power, nbUse, fail));
                }
                else if(type==Type.ELECTRIC){
                    attacks_electric.add(new Attack(name, type, power, nbUse, fail));
                }
                else{
                    attacks_normal.add(new Attack(name, type, power, nbUse, fail));     
                }  
            }
        }
        catch (IOException e) {
            System.err.println("Erreur lecture fichier : " + e.getMessage());
        }

        if(monster.getType()==Type.FIRE){

            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(attacks_fire.size());
                attacks[i] = attacks_fire.get(randomIndex);
                attacks_fire.remove(randomIndex);
            }

            for (int i = 2; i < 4; i++) {
                int randomIndex = random.nextInt(attacks_normal.size());
                attacks[i] = attacks_normal.get(randomIndex);
                attacks_normal.remove(randomIndex);
            }

            return attacks;
        }
        else if(monster.getType()==Type.WATER){
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(attacks_water.size());
                attacks[i] = attacks_water.get(randomIndex);
                attacks_water.remove(randomIndex);
            }

            for (int i = 2; i < 4; i++) {
                int randomIndex = random.nextInt(attacks_normal.size());
                attacks[i] = attacks_normal.get(randomIndex);
                attacks_normal.remove(randomIndex);
            }

            return attacks;
        }
        else if(monster.getType()==Type.GROUND){
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(attacks_ground.size());
                attacks[i] = attacks_ground.get(randomIndex);
                attacks_ground.remove(randomIndex);
            }

            for (int i = 2; i < 4; i++) {
                int randomIndex = random.nextInt(attacks_normal.size());
                attacks[i] = attacks_normal.get(randomIndex);
                attacks_normal.remove(randomIndex);
            }
            return attacks;
        }
        else if(monster.getType()==Type.NATURE){
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(attacks_nature.size());
                attacks[i] = attacks_nature.get(randomIndex);
                attacks_nature.remove(randomIndex);
            }

            for (int i = 2; i < 4; i++) {
                int randomIndex = random.nextInt(attacks_normal.size());
                attacks[i] = attacks_normal.get(randomIndex);
                attacks_normal.remove(randomIndex);
            }
            return attacks;
        }
        else{
            for (int i = 0; i < 2; i++) {
                int randomIndex = random.nextInt(attacks_electric.size());
                attacks[i] = attacks_electric.get(randomIndex);
                attacks_electric.remove(randomIndex);
            }

            for (int i = 2; i < 4; i++) {
                int randomIndex = random.nextInt(attacks_normal.size());
                attacks[i] = attacks_normal.get(randomIndex);
                attacks_normal.remove(randomIndex);
            }
            return attacks;
        } 
    }
    
}
