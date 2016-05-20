package parsers.csv;

import au.com.bytecode.opencsv.CSVReader;
import data.rawdata.csvdata.CsvData;
import data.rawdata.csvdata.CsvPattern;
import data.rawdata.csvdata.Host;
import data.rawdata.csvdata.Link;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 26.04.16.
 */
public class CsvParser {
    public CsvData getCsvData(File file, CsvPattern pattern) throws IOException {
        CsvData csvData = new CsvData();
        CSVReader reader = new CSVReader(new FileReader(file),';');
        String [] nextLine;
        List<Link> links = new LinkedList<>();
        List<Host> hosts = new LinkedList<>();
        while ((nextLine = reader.readNext()) != null){
            //в первой части линки
            //если там что то есть, то читаем линки
            //по стандарту 1ый и 2ой элемент это топология
            if (nextLine[0].compareTo("")!=0){
                Link link = new Link();
                link.setHostIpFrom(nextLine[0]);
                link.setHostIpTo(nextLine[1]);
                for(int i=0; i<nextLine.length; i++) {
                    if (pattern.getCsvKeys().get("channelCapacity")==i){
                        link.setChannelCapacity(Integer.parseInt(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("channelLoad")==i){
                        link.setChannelLoad(Float.parseFloat(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("channelValue")==i){
                        link.setChannelValue(Float.parseFloat(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("channelDamageCoast")==i){
                        link.setChannelDamageCoast(Integer.parseInt(nextLine[i]));
                    }
                }
                links.add(link);
            }
            //во второй части хосты
            //читаем если первая часть пустая
            else {
                Host host = new Host();
                for(int i=0; i<nextLine.length; i++) {
                    if (pattern.getCsvKeys().get("hostIp")==i){
                        host.setHostIp(nextLine[i]);
                    }
                    if (pattern.getCsvKeys().get("fqdn")==i){
                        host.setFqdn(nextLine[i]);
                    }
                    if (pattern.getCsvKeys().get("informationValue")==i){
                        host.setInformationValue(Float.parseFloat(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("load")==i){
                        host.setLoad(Float.parseFloat(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("possibleDamage")==i){
                        host.setPossibleDamage(Float.parseFloat(nextLine[i]));
                    }
                    if (pattern.getCsvKeys().get("type")==i){
                        host.setType(nextLine[i]);
                    }
                }
                hosts.add(host);
            }
        }
        csvData.setLinks(links);
        csvData.setHosts(hosts);
        return csvData;
    }
}
