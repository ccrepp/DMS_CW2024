package dev.ccr.dmscw2024.utility;

import javafx.scene.media.AudioClip;

/**
 * Audio Manager - manages audio effects, projectiles specifically
 */
public class AudioManager {
    /**
     * plays audio effect with specified sound file and volume
     * @param soundFile sound file
     * @param volume volume to be played at
     */
    public static void playAudioEffect (String soundFile, double volume) {
        AudioClip audio = new AudioClip(AudioManager.class.getResource(soundFile).toExternalForm());
        audio.setVolume(volume);
        audio.play();
    }
}
