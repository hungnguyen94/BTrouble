package com.sem.btrouble.tools;

import java.io.IOException;
import java.util.Observable;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.PlayerEvent;

import java.util.Observer;

/**
 * Observes the sound.
 * @author Martin
 *
 */
public class SoundObserver implements Observer {

    private Audio wavEffect;

    /**
     * Construct a sound observer.
     */
    public SoundObserver() {

    }

    /**
     * Update the sounds.
     * @param observable the object to observe
     * @param arg the event
     */
    public void update(Observable observable, Object arg) {
        if (arg instanceof ControllerEvent) {
            ControllerEvent event = (ControllerEvent) arg;
            if (event.getId() == ControllerEvent.GAMELOST) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("fail-trombone-02.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
            if (event.getId() == ControllerEvent.GAMEWON) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("Winning-sound-effect.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
            if (event.getId() == ControllerEvent.RESTARTROOM
                    || event.getId() == ControllerEvent.NEXTROOM) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("soundscrate-17-woosh2.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
        }
        if (arg instanceof PlayerEvent) {
            PlayerEvent event = (PlayerEvent) arg;
            if (event.getId() == PlayerEvent.SHOOT) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("soundscrate-17-woosh2.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
        }
    }

}
