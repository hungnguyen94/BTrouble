package com.sem.btrouble.tools;

import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.observering.EventObserver;
import com.sem.btrouble.observering.PlayerObserver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Class which represents the logger.
 *
 */
public class Logger implements EventObserver, PlayerObserver {

    private FileWriter fw;
    private BufferedWriter bw;
    private boolean doConsoleLog;
    public static final String DEFAULT_LOGGER_PATH = "log.txt";

    /**
     * Initializes the logger, opening the file to which the log data is
     * written. This method should be called before any other Logger method is
     * called.
     * 
     * @param fileName
     *            should be a string representing the file name
     * @param doConsoleLog
     *            should be a boolean representing whether to write log lines to
     *            the console.
     */
    public Logger(String fileName, boolean doConsoleLog) {
        try {
            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            this.doConsoleLog = doConsoleLog;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs a GameEvent to the log file. 
     * @param event
     *            should be the GameEvent to be written to the log file.
     */
    private void log(GameEvent event) {
        log(event.toString());
    }
    
    /**
     * Logs a String to the log file. This method writes a new line in the
     * log file, containing a Date stamp and the output of the toString() method
     * of the provided GameEvent.
     * 
     * @param event
     *            should be the String to be written to the log file.
     */
    private void log(String event) {
        try {
            Date date = new Date();
            String currentDate = date.toString();
            String temp = currentDate + " " + event;
            bw.write(temp);
            if (doConsoleLog) {
                System.out.println("Log: " + event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameEvent gameEvent) {
        log(gameEvent);
    }

    @Override
    public void shotaRope() {
        log("<PlayerEvent: shot a rope>");
        
    }

    @Override
    public void lostaLife() {
        log("<PlayerEvent: lost a life>");
        
    }

    @Override
    public void gainedaLife() {
        log("<PlayerEvent: gained a life>");
    }

    @Override
    public void collidedWithBubble() {
        log("<PlayerEvent: collided with bubble>");
    }

    @Override
    public void collidedLeft() {
        log("<PlayerEvent: collided at left side>");
    }

    @Override
    public void collidedRight() {
        log("<PlayerEvent: collided at right side>");
    }

    @Override
    public void poppedaBubble() {
        log("<PlayerEvent: popped a bubble>");
    }

}
