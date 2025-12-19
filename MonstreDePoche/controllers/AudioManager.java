package MonstreDePoche.controllers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    // On garde une référence statique du clip pour pouvoir l'arrêter plus tard
    private static Clip currentClip;

    public static void playMusic(String filePath) {
        try {
            // Si une musique joue déjà, on l'arrête avant d'en lancer une nouvelle
            stopMusic();

            File musicPath = new File(filePath);

            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                currentClip = AudioSystem.getClip();
                currentClip.open(audioInput);
                currentClip.start();
                
                currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Fichier audio introuvable !");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        // On vérifie si le clip existe et s'il est en train de jouer
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close(); // Libère les ressources système
        }
    }
}
