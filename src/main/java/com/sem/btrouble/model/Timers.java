package com.sem.btrouble.model;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which controls all the timers in the game.
 */
public class Timers {

    private Timer levelTimer;
    private Timer countdownTimer;
    private int levelTimerCounter;
    private int countdownCounter;
    private int additionalTime;
    public static final int LEVEL_MAX_DURATION = 500;
    private static final int COUNTDOWN_MAX_DURATION = 30;
    private static final int TIMER_SPEED = 100;

    /**
     * Constructor for the timers class. Initializes the timers, but doesn't
     * start them.
     * 
     * @param delay
     *            - delay before the timer actually starts.
     */
    public Timers(int delay) {
        additionalTime = 0;
        levelTimerCounter = LEVEL_MAX_DURATION;
        countdownCounter = COUNTDOWN_MAX_DURATION;
        levelTimer = new Timer(TIMER_SPEED, new LevelTimerActionListener());
        levelTimer.setInitialDelay(delay);
        countdownTimer = new Timer(TIMER_SPEED, new CountdownTimerActionListener());
    }

    /**
     * Restarts the game timer.
     */
    public void restartTimer() {
        levelTimer.stop();
        countdownTimer.stop();
        levelTimerCounter = LEVEL_MAX_DURATION + additionalTime;
        countdownCounter = COUNTDOWN_MAX_DURATION;
        countdownTimer.restart();
    }

    /**
     * Restarts only the timer (not the game).
     */
    public void survivalTimer() {
        levelTimerCounter = LEVEL_MAX_DURATION + additionalTime;
        countdownCounter = COUNTDOWN_MAX_DURATION;
    }
    /**
     * Reset the level timer counter.
     */
    public void resetLevelTimerCounter() {
        levelTimerCounter = LEVEL_MAX_DURATION;
    }

    /**
     * Add time to the clock.
     * @param time time to be added.
     */
    public void addAdditionalTime(int time) {
        additionalTime += time;
    }

    /**
     * Reset additional time to zero.
     * Used for powerups.
     */
    public void resetAdditionalTime() {
        this.additionalTime = 0;
    }

    /**
     * Set level timer. Cannot go higher than MAX
     * @param time increase time with this value.
     */
    public void increaseLevelTimerCounter(int time) {
        levelTimerCounter += time;
        if(levelTimerCounter > LEVEL_MAX_DURATION + additionalTime) {
            levelTimerCounter = LEVEL_MAX_DURATION + additionalTime;
        }
    }

    /**
     * Return the amount of time left in the level in milliseconds. Multiplies
     * the TIMER_SPEED by the Counter to get the time in ms.
     * 
     * @return - time left in the game in milliseconds.
     */
    public int getLevelTimeLeft() {
        return levelTimerCounter * TIMER_SPEED;
    }

    /**
     * Returns the max game duration in milliseconds. Multiplies the value with
     * the TIMER_SPEED to get the time in ms.
     * 
     * @return - Max time in a level.
     */
    public int getLevelMaxDuration() {
        return (LEVEL_MAX_DURATION + additionalTime) * TIMER_SPEED;
    }

    /**
     * Returns the amount of time before the game starts in ms.
     * 
     * @return - countdown value of the countdownTimer in ms
     */
    public int getCountdownTimeLeft() {
        return countdownCounter * TIMER_SPEED;
    }

    /**
     * Returns the max countdown duration in milliseconds. Multiplies the value
     * with the TIMER_SPEED to get the time in ms.
     * 
     * @return - Max countdown time.
     */
    public int getCountdownMaxDuration() {
        return COUNTDOWN_MAX_DURATION * TIMER_SPEED;
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
        /**
         * Perform action.
         * @param event the event
         */
        public void actionPerformed(ActionEvent event) {
//             System.out.println("levelTimerCounter: " + levelTimerCounter);
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
        /**
         * Perform action.
         * @param event the event
         */
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
