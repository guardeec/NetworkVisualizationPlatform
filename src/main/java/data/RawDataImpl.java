package data;

import data.rawdata.csvdata.CsvData;
import data.rawdata.csvdata.CsvPattern;
import data.rawdata.maxpatroldata.MaxPatrolData;

import java.io.File;
import java.util.List;

/**
 * Created by Guardeec on 27.04.16.
 */
public interface RawDataImpl {
    public CsvData getCsv();
    public MaxPatrolData getMaxPatrolLog();

    public boolean setCsv(File csv, String patternName);
    public boolean setMaxPatrolLog(File maxPatrolLog);
    public boolean setPattern(CsvPattern csvPattern);
    public boolean removeCsvPattern(String patternName);
    public List<CsvPattern> getCsvPatternList();
}
