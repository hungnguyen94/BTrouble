package com.sem.btrouble.tools;

import java.io.IOException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.Event;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.observering.Observer;

/**
 * Observes the sound.
 * 
 * @author Martin
 *
 */
public class SoundObserver implements Observer {

    private Audio wavEffect;

    /**
     * The update method for audio. When an event is triggered, the audio is
     * updated.
     * 
     * @param event
     *            the event that is triggered
     */
    public void update(ControllerEvent event) {
        try {
            switch (event) {
                case GAMELOST:
                    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader
                            .getResourceAsStream("fail-trombone-02.wav"));

                    wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                    SoundStore.get().poll(0);
                    break;
                case GAMEWON:
                    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader
                            .getResourceAsStream("Winning-sound-effect.wav"));

                    wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                    SoundStore.get().poll(0);
                    break;
                case NEXTROOM:
                case RESTARTROOM:
                    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader
                            .getResourceAsStream("soundscrate-17-woosh2.wav"));

                    wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                    SoundStore.get().poll(0);
                    break;
                default:
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the sounds.
     * 
     * @param event
     *            the event
     */
    public void update(PlayerEvent event) {
        switch ((PlayerEvent) event) {
            case SHOOT:
                try {
                    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader
                            .getResourceAsStream("soundscrate-17-woosh2.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Event event) {
        if (event instanceof PlayerEvent) {
            update((PlayerEvent) event);
        } else if (event instanceof ControllerEvent) {
            update((ControllerEvent) event);
        }
    }
}
