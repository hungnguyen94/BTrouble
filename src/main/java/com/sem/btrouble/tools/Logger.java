package com.sem.btrouble.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import com.sem.btrouble.event.GameEvent;

public class Logger {

  private static FileWriter fw;
  private static BufferedWriter bw;

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
