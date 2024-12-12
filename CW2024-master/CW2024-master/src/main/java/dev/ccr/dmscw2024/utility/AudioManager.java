package dev.ccr.dmscw2024.utility;

import javafx.scene.media.AudioClip;

public class AudioManager {
    public static void playAudioEffect (String soundFile, double volume) {
        AudioClip audio = new AudioClip(AudioManager.class.getResource(soundFile).toExternalForm());
        audio.setVolume(volume);
        audio.play();
    }
}
