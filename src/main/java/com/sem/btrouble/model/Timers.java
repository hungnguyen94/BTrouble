package com.sem.btrouble.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Class which controls all the timers in the game.
 */
public class Timers {

    private Timer levelTimer;
    private Timer countdownTimer;
    private int levelTimerCounter;
    private int countdownCounter;
    // Max duration in seconds * 10
    private final int levelMaxDuration = 500;
    // Countdown delay before level starts in seconds * 10
    private final int countdownMaxDuration = 30;
    private final int timerSpeed = 100;

    /**
     * Constructor for the timers class. Initializes the timers, but doesn't
     * start them.
     * 
     * @param delay
     *            - delay before the timer actually starts.
     */
    public Timers(int delay) {
        levelTimerCounter = levelMaxDuration;
        countdownCounter = countdownMaxDuration;
        levelTimer = new Timer(timerSpeed, new LevelTimerActionListener());
        levelTimer.setInitialDelay(delay);
        countdownTimer = new Timer(timerSpeed, new CountdownTimerActionListener());
    }

    /**
     * Restarts the game timer.
     */
    public void restartTimer() {
        levelTimer.stop();
        countdownTimer.stop();
        levelTimerCounter = levelMaxDuration;
        countdownCounter = countdownMaxDuration;
        countdownTimer.restart();
    }
    
    /**
     * Restarts the game timer without countdown.
     */
    public void restartTimerWithoutCountdown() {
        levelTimerCounter = levelMaxDuration;

    }

    public void setLevelTimerCounter(int duration) {
        levelTimer.setInitialDelay(duration);
    }

    /**
     * Return the amount of time left in the level in milliseconds. Multiplies
     * the timerSpeed by the Counter to get the time in ms.
     * 
     * @return - time left in the game in milliseconds.
     */
    public int getLevelTimeLeft() {
        return levelTimerCounter * timerSpeed;
    }

    /**
     * Returns the max game duration in milliseconds. Multiplies the value with
     * the timerSpeed to get the time in ms.
     * 
     * @return - Max time in a level.
     */
    public int getLevelMaxDuration() {
        return levelMaxDuration * timerSpeed;
    }

    /**
     * Returns the amount of time before the game starts in ms.
     * 
     * @return - countdown value of the countdownTimer in ms
     */
    public int getCountdownTimeLeft() {
        return countdownCounter * timerSpeed;
    }

    /**
     * Returns the max countdown duration in milliseconds. Multiplies the value
     * with the timerSpeed to get the time in ms.
     * 
     * @return - Max countdown time.
     */
    public int getCountdownMaxDuration() {
        return countdownMaxDuration * timerSpeed;
    }

    /**
     * Check if the level timer is running.
     * 
     * @return - true if the level timer is running.
     */
    public boolean getLevelTimerRunning() {
        return levelTimer.isRunning();
    }

    /**
     * Pauses/stops the timer.
     */
    public void stopLevelTimer() {
        levelTimer.stop();
    }

    /**
     * Check if the countdown is running.
     * 
     * @return - true if the countdown is running.
     */
    public boolean getCountdownRunning() {
        return countdownTimer.isRunning();
    }

    /**
     * ActionListener for the levelTimer timer. Specifies what actions should be
     * executed every timer cycle.
     */
    class LevelTimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // System.out.println("levelTimerCounter: " + levelTimerCounter);
            levelTimerCounter--;
            if (levelTimerCounter <= 0) {
                levelTimer.stop();
            }
        }
    }

    /**
     * ActionListener for the countdownTimer timer. Specifies what actions
     * should be executed every timer cycle.
     */
    class CountdownTimerActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // System.out.println("countdownTimerCounter: " + countdownCounter);
            countdownCounter--;
            if (countdownCounter <= 0) {
                levelTimer.restart();
                countdownTimer.stop();
            }
        }
    }

}
