package nl.bastiaansierd.datalogger.data;

import java.util.Date;

public interface DataLog {
    void schrijfLog(Date datum, String whodunnit, String logTekst);
}
