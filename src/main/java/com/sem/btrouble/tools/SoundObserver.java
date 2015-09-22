package com.sem.btrouble.tools;

import java.io.IOException;
import java.util.Observable;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

import com.sem.btrouble.event.ControllerEvent;
import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.event.PlayerEvent;

import java.util.Observer;

public class SoundObserver implements Observer {
	
	private Audio wavEffect;

	public SoundObserver() {

	}

	public void update(Observable observable, Object arg) {
		if (arg instanceof ControllerEvent) {
			ControllerEvent event = (ControllerEvent) arg;
			if(event.getId() == ControllerEvent.GAMEOVER) {
			    try {
				    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("fail-trombone-02.wav"));
			        } catch (IOException e) {
				    e.printStackTrace();
				}
				wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				SoundStore.get().poll(0);
			}
			if(event.getId() == ControllerEvent.RESTARTROOM) {
			    try {
				    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("soundscrate-17-woosh2.wav"));
			        } catch (IOException e) {
				    e.printStackTrace();
				}
				wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				SoundStore.get().poll(0);
			}
		}
		if(arg instanceof PlayerEvent) {
			PlayerEvent event = (PlayerEvent) arg;
			if(event.getId() == PlayerEvent.SHOOT) {
				wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
				SoundStore.get().poll(0);
			}
		}
	}

}
