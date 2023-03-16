package br.org.edn.my2dgame.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

import static br.org.edn.my2dgame.main.Constants.DIRECTORY_BASE_SOUND;
import static br.org.edn.my2dgame.main.Constants.EXTENSION_WAV;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource(DIRECTORY_BASE_SOUND + "BlueBoyAdventure" + EXTENSION_WAV);
        soundURL[1] = getClass().getResource(DIRECTORY_BASE_SOUND + "coin" + EXTENSION_WAV);
        soundURL[2] = getClass().getResource(DIRECTORY_BASE_SOUND + "powerup" + EXTENSION_WAV);
        soundURL[3] = getClass().getResource(DIRECTORY_BASE_SOUND + "unlock" + EXTENSION_WAV);
        soundURL[4] = getClass().getResource(DIRECTORY_BASE_SOUND + "fanfare" + EXTENSION_WAV);
        soundURL[5] = getClass().getResource(DIRECTORY_BASE_SOUND + "hitmonster" + EXTENSION_WAV);
        soundURL[6] = getClass().getResource(DIRECTORY_BASE_SOUND + "receivedamage" + EXTENSION_WAV);
        soundURL[7] = getClass().getResource(DIRECTORY_BASE_SOUND + "parry" + EXTENSION_WAV);
        soundURL[8] = getClass().getResource(DIRECTORY_BASE_SOUND + "levelup" + EXTENSION_WAV);
        soundURL[9] = getClass().getResource(DIRECTORY_BASE_SOUND + "cursor" + EXTENSION_WAV);
        soundURL[10] = getClass().getResource(DIRECTORY_BASE_SOUND + "burning" + EXTENSION_WAV);
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error loading sound: " + soundURL[i]);
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}