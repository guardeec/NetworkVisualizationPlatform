package data.rawdata.maxpatroldata;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 27.03.16.
 */
public class MaxPatrolData extends DefaultHandler {

    private List<Vulnerability> vulnerabilityList = new LinkedList<>();
    private List<Host> hostList = new LinkedList<>();
    private Vulnerability vulnerability;
    private Host host;

    private String value;
    private boolean flagVulnerabilites = true;
    private boolean flagData = false;

    public List<Vulnerability> getVulnerabilityList(){
        return this.vulnerabilityList;
    }

    public List<Host> getHosts(){
        return this.hostList;
    }

    public String getDataAsString(){
        String output = "";
        for (Host analizedHost : hostList){
            output += analizedHost.getDataAsString(vulnerabilityList);
            output += "\n";
        }
        return output;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (flagVulnerabilites){
            switch (qName){
                case "vulner" :{
                    vulnerability = new Vulnerability();
                    vulnerability.setId(Integer.parseInt(attributes.getValue(0)));
                    break;
                }
                case "cvss":{
                    vulnerability.setTempScore(Float.parseFloat(attributes.getValue(0)));
                    vulnerability.setBaseScore(Float.parseFloat(attributes.getValue(1)));
                    vulnerability.setTempScoreDecomp(attributes.getValue(2));
                    vulnerability.setBaseScoreDecomp(attributes.getValue(3));
                    break;
                }
                case "global_id":{
                    vulnerability.setGlobalIdName(attributes.getValue(0));
                    vulnerability.setGlobalIdValue(attributes.getValue(1));
                    break;
                }

            }
        }

        if (flagData){
            switch (qName){
                case "host":{
                    host = new Host();
                    host.setHostIp(attributes.getValue(0));
                    host.setStartTime(attributes.getValue(1));
                    host.setTask(Integer.parseInt(attributes.getValue(2)));
                    host.setHostUid(Integer.parseInt(attributes.getValue(3)));
                    host.setScan(Integer.parseInt(attributes.getValue(4)));
                    host.setFqdn(attributes.getValue(5));
                    host.setStopTime(attributes.getValue(6));
                    host.setPrimary(attributes.getValue(7));
                    host.setExitcode(Integer.parseInt(attributes.getValue(8)));
                    host.setScanStatus(Integer.parseInt(attributes.getValue(9)));
                    host.setNetbios(attributes.getValue(10));
                    break;
                }
                case "vulner" :{
                    host.getVulnerIds().add(Integer.parseInt(attributes.getValue(0)));
                    break;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (flagVulnerabilites) {
            switch (qName) {
                case "vulners": {
                    flagVulnerabilites = false;
                    flagData = true;
                    break;
                }
                case "title": {
                    vulnerability.setTitle(value);
                    break;
                }
                case "description": {
                    vulnerability.setDescription(value);
                    break;
                }
                case "short_description":{
                    vulnerability.setShortDescription(value);
                    break;
                }
                case "how_to_fix": {
                    vulnerability.setHowToFix(value);
                    break;
                }
                case "links": {
                    vulnerability.setLink(value);
                    break;
                }
                case "vulner": {
                    vulnerabilityList.add(vulnerability);
                    break;
                }
            }
        }

        if (flagData){
            switch (qName){
                case "qualifier_rule":{
                    host.setQualifierRule(value);
                    break;
                }
                case "scanner":{
                    host.setScannerVersion(value);
                    break;
                }
                case "host":{
                    hostList.add(host);
                    break;
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        value = new String(ch, start, length);
    }
}
