package com.sem.btrouble.tools;

import com.sem.btrouble.event.GameEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

    private static FileWriter fw;
    private static BufferedWriter bw;

    /**
     * Initializes the logger, opening the file to which the log data is
     * written. This method should be called before any other Logger method is
     * called.
     */
    public static void initLog() {
	try {
	    File file = new File("log.txt");

	    if (!file.exists()) {
		file.createNewFile();
	    }

	    fw = new FileWriter(file.getAbsoluteFile());
	    bw = new BufferedWriter(fw);
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
    public static void log(GameEvent event) {
	try {
	    Date date = new Date();
	    String currentDate = date.toString();
	    String temp = currentDate + " " + event.toString();
	    bw.write(temp);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
