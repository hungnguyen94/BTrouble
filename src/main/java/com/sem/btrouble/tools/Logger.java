package com.sem.btrouble.tools;

import com.sem.btrouble.event.GameEvent;
import com.sem.btrouble.observering.EventObserver;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Class which represents the logger.
 *
 */
public class Logger implements EventObserver {

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
     * Logs a GameEvent to the log file. This method writes a new line in the
     * log file, containing a Date stamp and the output of the toString() method
     * of the provided GameEvent.
     * 
     * @param event
     *            should be the GameEvent to be written to the log file.
     */
    private void log(GameEvent event) {
        try {
            Date date = new Date();
            String currentDate = date.toString();
            String temp = currentDate + " " + event.toString();
            bw.write(temp);
            if (doConsoleLog) {
                System.out.println("Log: " + event.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameEvent gameEvent) {
        log(gameEvent);
    }

}
