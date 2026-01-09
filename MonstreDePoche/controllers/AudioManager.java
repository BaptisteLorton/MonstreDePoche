package MonstreDePoche.controllers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private static Clip currentClip;

    public static void playMusic(String filePath) {
        try {
            stopMusic();

            File musicPath = new File(filePath);

            if (musicPath.exists()) {
                AudioInputStream rawInput = AudioSystem.getAudioInputStream(musicPath);
                AudioFormat baseFormat = rawInput.getFormat();

                // Format standardisé (Little Endian pour Linux)
                AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    44100.0f, 16, 2, 4, 44100.0f, false
                );

                // Conversion du flux
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(targetFormat, rawInput);
                
                // --- MODIFICATION ICI ---
                // On définit explicitement les infos de la ligne souhaitée
                DataLine.Info info = new DataLine.Info(Clip.class, targetFormat);
                
                // On demande au système une ligne correspondant à ces infos
                currentClip = (Clip) AudioSystem.getLine(info);
                
                currentClip.open(audioInput);
                currentClip.start();
                currentClip.loop(Clip.LOOP_CONTINUOUSLY);
                
            } else {
                System.out.println("Fichier audio introuvable : " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException e) {
            System.err.println("Erreur audio critique sur Linux pour : " + filePath);
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (currentClip != null) {
            currentClip.stop();
            currentClip.flush();
            currentClip.close();
        }
    }
}