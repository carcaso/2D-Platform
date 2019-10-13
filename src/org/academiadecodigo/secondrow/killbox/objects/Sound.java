package org.academiadecodigo.secondrow.killbox.objects;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    private Clip clip;
    private int size;

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("music.wav").getAbsoluteFile());

            System.out.println(size);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(1000000);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

    }

    public long getSize(){
        return size;
    }

}
