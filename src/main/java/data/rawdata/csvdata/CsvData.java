package data.rawdata.csvdata;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 26.04.16.
 */
public class CsvData {
    private List<Link> links = new LinkedList<>();
    private List<Host> hosts = new LinkedList<>();

    public CsvData(){
    }

    public Host getHostByKeys(String hostIp, String hostFqdn){
        for (Host host : hosts){
            if (    host.getHostIp().compareTo(hostIp)==0 &&
                    host.getFqdn().compareTo(hostFqdn)==0
                    ){
                return host;
            }
        }
        return null;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }
}
