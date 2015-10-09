package com.sem.btrouble.tools;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.event.PlayerEvent;
import com.sem.btrouble.observering.EventObserver;

/**
 * Observes the sound.
 * @author Martin
 *
 */
public class SoundObserver implements EventObserver {

    private Audio wavEffect;
    
    /**
     * Update the sounds.
     * @param observable the object to observe
     * @param arg the event
     */
    @Override
    public void update(GameEvent event) {
        if (event instanceof ControllerEvent) {
            ControllerEvent controllerEvent = (ControllerEvent) event;
            if (controllerEvent.getId() == ControllerEvent.GAMELOST) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("fail-trombone-02.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
            if (controllerEvent.getId() == ControllerEvent.GAMEWON) {
                try {
                    wavEffect = AudioLoader.getAudio("WAV",
                            ResourceLoader.getResourceAsStream("Winning-sound-effect.wav"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
                SoundStore.get().poll(0);
            }
            if (controllerEvent.getId() == ControllerEvent.RESTARTROOM
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
        if (event instanceof PlayerEvent) {
            PlayerEvent playerEvent = (PlayerEvent) event;
            if (playerEvent.getId() == PlayerEvent.SHOOT) {
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
