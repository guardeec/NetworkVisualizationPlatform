package data.rawdata.csvdata;

import java.util.*;

/**
 * Created by Guardeec on 27.04.16.
 */
public class CsvPattern {
    private Map<String, Integer> csvKeys;
    private String patternName;
    private List<String> possibleNames;

    public CsvPattern(String patternName) {
        this.patternName = patternName;
        possibleNames = new ArrayList<>(Arrays.asList(
                PossibleNames.linkChannelCapacity,
                PossibleNames.linkChannelLoad,
                PossibleNames.linkChannelValue,
                PossibleNames.linkChannelDamageCoast,
                PossibleNames.hostHostIp,
                PossibleNames.hostFqdn,
                PossibleNames.hostInformationValue,
                PossibleNames.hostLoad,
                PossibleNames.hostPossibleDamage,
                PossibleNames.hostType
        ));
        csvKeys = new HashMap<>();
        csvKeys.put("HostIpFrom",0);
        csvKeys.put("HostIpTo",1);
    }

    public Map<String, Integer> getCsvKeys() {
        return csvKeys;
    }

    public void addCsvKey(String name, Integer columnNumber){
        if (!possibleNames.contains(name)){
            throw new IllegalArgumentException("Metrics Name is Not Found");
        }
        if (columnNumber<2){
            throw new IllegalArgumentException("Column number can not be negative. First column is HostFromIp, Second - HostToIp");
        }
        csvKeys.put(name, columnNumber);
    }

    public String getPatternName() {
        return patternName;
    }

    public static class PossibleNames{
        public static final String linkChannelCapacity = "channelCapacity";
        public static final String linkChannelLoad = "channelLoad";
        public static final String linkChannelValue = "channelValue";
        public static final String linkChannelDamageCoast = "channelDamageCoast";

        public static final String hostHostIp = "hostIp";
        public static final String hostFqdn = "fqdn";
        public static final String hostInformationValue = "informationValue";
        public static final String hostLoad = "load";
        public static final String hostPossibleDamage = "possibleDamage";
        public static final String hostType = "type";
    }
}
