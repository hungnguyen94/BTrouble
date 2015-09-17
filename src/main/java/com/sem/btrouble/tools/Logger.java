package com.sem.btrouble.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

    private static FileWriter fw;
    private static BufferedWriter bw;
    
    public static void initLog () throws IOException {
        File file = new File("log.txt");
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
    }
    
    public static void log (String message) throws IOException {
        Date date = new Date();
        String currentDate = date.toString();
        String temp = currentDate + " " + message;
        bw.write(temp);
    }
    
}
