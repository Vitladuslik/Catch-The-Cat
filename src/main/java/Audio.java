import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    void play_music(String music_location) {
        try {
            File music_path = new File(music_location);
            if (music_path.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(music_path);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("Не удается найти файл!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

        void play_meow (String music_location)
        {
            try {
                File music_path = new File(music_location);

                if (music_path.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(music_path);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                } else {
                    System.out.println("Не удается найти файл!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        void play_end (String music_location)
        {
            try {
                File music_path = new File(music_location);
                if (music_path.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(music_path);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                } else {
                    System.out.println("Не удается найти файл!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

