package com.sem.btrouble.tools;

import com.sem.btrouble.event.GameEvent;

import java.util.Observable;
import java.util.Observer;

public class GameObserver implements Observer {

    private boolean consoleLog;

    /**
     * GameObserver, used to log the observed GameEvents to a file.
     * 
     * @param consoleLog
     *            should be a boolean representing whether the observed
     *            GameEvents should be logged in the console.
     */
    public GameObserver(boolean consoleLog) {
        this.consoleLog = consoleLog;
        Logger.initLog();
    }

    /**
     * Method called when the observed object fires an event, writing the event
     * to the log file.
     */
    public void update(Observable observable, Object arg) {
        if (arg instanceof GameEvent) {
            Logger.log((GameEvent) arg);
            if (consoleLog) {
                System.out.println("Log: " + ((GameEvent) arg).toString());
            }
        } else {
            System.out.println("Logger: unable to log event " + arg.getClass().toString());
        }
    }

}
