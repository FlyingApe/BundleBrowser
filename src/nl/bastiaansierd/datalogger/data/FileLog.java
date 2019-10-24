package nl.bastiaansierd.datalogger.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class FileLog implements DataLog {
    private final static String dirPath = "src/nl/bastiaansierd/datalogger/";
    private String archive;
    private PrintWriter log;

    public FileLog() {
        try{
            File logFile = new File(dirPath + "log.txt");
            Scanner scanner = new Scanner(logFile);
            StringBuilder sBuilder = new StringBuilder();
            while (scanner.hasNextLine()){
                sBuilder.append(scanner.nextLine() + "\n");
            }
            setArchive(sBuilder.toString());

        }
        catch (FileNotFoundException fnfe){
            System.out.println("Geen logfile gevonden, een nieuwe wordt aangemaakt");
        }

        try {
            log = new PrintWriter(dirPath + "log.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public void schrijfLog(Date datum, String logTekst, String whodunnit){
        log.println(datum.toString() + " :: " + whodunnit + " :: " + logTekst);
        if(archive != null){log.print(archive);}
        log.close();
    }
}
