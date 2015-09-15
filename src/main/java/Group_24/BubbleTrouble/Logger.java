package Group_24.BubbleTrouble;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private FileWriter fw;
    private BufferedWriter bw;
    
    public void initLog () throws IOException {
        File file = new File("log.txt");
        
        if (!file.exists()) {
            file.createNewFile();
        }
        
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
    }
    
    public void log (String message) throws IOException {
        bw.write(message);
    }
    
}
