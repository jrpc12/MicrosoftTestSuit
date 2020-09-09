package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class Logger {


    public static Logger objectInstance;
    private String fileNamePrefix = "result";
    private String fileName;
    private Logger(){
        initFile();
    }
    public static Logger getObjectInstance(){
        if(objectInstance == null){
            objectInstance = new Logger();
        }
        return objectInstance;
    }

    private void initFile(){

        try{
            fileName = fileNamePrefix  + "_" + (new Timestamp(System.currentTimeMillis()).getTime()) + ".txt";
            File myFile = new File(fileName);
            myFile.createNewFile();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    
    public void info(String text){    

        try {
            /*
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(" - " + text + "\n");
            myWriter.close();     
            */
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
            out.println(" - " + text);
            out.close();
        
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
