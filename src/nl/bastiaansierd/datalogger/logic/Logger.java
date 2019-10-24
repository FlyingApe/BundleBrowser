package nl.bastiaansierd.datalogger.logic;

import nl.bastiaansierd.datalogger.data.DataLog;
import nl.bastiaansierd.datalogger.data.FileLog;

import java.util.Date;

public class Logger {
    public static void log(String whodunnit, String logTekst){
        Date datum = new Date();
        DataLog fileLog = new FileLog();
        fileLog.schrijfLog(datum, whodunnit, logTekst);
    }
}
