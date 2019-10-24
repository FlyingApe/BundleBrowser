package nl.bastiaansierd.bundleb.data.dataAccessConnectors;

import nl.bastiaansierd.datalogger.logic.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

public class MarkdownFileLoader {
    private String filePath = null;
    private String FileAsString = null;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAsString(){
        if (FileAsString == null){
            loadFile();
        }
        return FileAsString;
    }

    public void loadFile(String filePath){
        Scanner scanner = null;
        try {
            File inputFile;
            inputFile = new File(filePath);
            scanner = new Scanner(inputFile);

            StringBuilder sBuilder = new StringBuilder();
            while (scanner.hasNextLine()){
                sBuilder.append(scanner.nextLine() + "\n");
            }
            FileAsString = sBuilder.toString();
        } catch (FileNotFoundException fnfe) {
            String fullPath = Paths.get("./").toAbsolutePath().normalize().toString() + "\\" +  filePath.replace("/", "\\");
            Logger.log("MardownfileLoader.loadFile(String filePath)", fnfe.getMessage() + ". Path given: " + fullPath);
        }
        finally {
            assert scanner != null;
            scanner.close();
        }
    }

    private void loadFile(){
        if(filePath != null) {
            loadFile(filePath);
        } else {
            Logger.log("MarkdownFileLoader.loadfile()","No filePath set!");
        }
    }

    public static String toString(String filePath){
        MarkdownFileLoader fLoader = new MarkdownFileLoader();
        fLoader.loadFile(filePath);
        return fLoader.getAsString();
    }
}
