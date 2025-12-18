package MonstreDePoche.models;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    public static void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                
                 //Pour jouer en boucle :
                 clip.loop(Clip.LOOP_CONTINUOUSLY);
                
            } else {
                System.out.println("Fichier audio introuvable !");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
