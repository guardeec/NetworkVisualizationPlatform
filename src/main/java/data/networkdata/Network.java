package data.networkdata;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 26.04.16.
 */

public class Network {
    //метрики сети
    private String name;
    private Date date;
    private String description;
    //список хостов
    private List<Link> links;
    //список связей
    private List<Host> hosts;

    public Network(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.links = new LinkedList<>();
        this.hosts = new ArrayList<>();
    }

    public Network(){

    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLink(Link link) {
        this.links.add(link);
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public void setHost(Host host) {
        this.hosts.add(host);
    }

    public String getNetworkAsString(){
        String net = "\n\n\n\n";
        net += "name: "+name+"\n";
        net += "uploadDate: "+date.getTime()+"\n";
        net += "info:"+description+"\n";
        net += "   \n---------------Nodes info:\n";
        for (Host host : hosts){
            net += "Ip:"+host.getIp()+"\n";
            net += "Fqdn:"+host.getFqdn()+"\n";
            net += "Type:"+host.getType()+"\n";
            net += "InfVal:"+host.getInformationValue()+"\n";
            net += "Load:"+host.getLoad()+"\n";
            net += "PossibleDamage:"+host.getPossibleDamage()+"\n";
            net += "VulCount:"+host.getVulnerabilityCount()+"\n";
            net += "VilHighLvl:"+host.getVulnerabilityHighestLevel()+"\n";
            net += "\n";
        }
        net += "   \n---------------Links info:\n";
        for (Link link : links){
            net += "IpFrom:"+link.getHostIpFrom()+"\n";
            net += "IpTo:"+link.getHostIpTo()+"\n";
            net += "ChCap:"+link.getChannelCpacity()+"\n";
            net += "ChDamCoast:"+link.getChannelDamageCoast()+"\n";
            net += "ChLoad:"+link.getChannelLoad()+"\n";
            net += "ChValue:"+link.getChannelValue()+"\n";
            net += "\n";
        }
        return net;
    }

}
