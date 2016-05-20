package parsers.graphicalmodels;

import data.graphicalmodelsdata.ParamsGraph;
import data.networkdata.Host;
import data.networkdata.Link;
import data.networkdata.Network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guardeec on 27.04.16.
 */
public class GraphParser {
    public ParamsGraph parseToGraph(Network network){
        ParamsGraph paramsGraph = new ParamsGraph();
        Map<String, Integer> nameAndId = new HashMap<>();
        int counter = 0;
        for (Host host : network.getHosts()){
            int group = 6; //6=unknown
            switch (host.getType()){
                case "pc" : {
                    group = 0;
                    break;
                }
                case "database" : {
                    group = 1;
                    break;
                }
                case "mobile" : {
                    group = 2;
                    break;
                }
                case "arduino" : {
                    group = 3;
                    break;
                }
                case "firewall" : {
                    group = 4;
                    break;
                }
                case "camera" : {
                    group = 5;
                    break;
                }
                case "internet" : {
                    group = 7;
                    break;
                }
                case "server" : {
                    group = 8;
                    break;
                }
                case "router" : {
                    group = 9;
                    break;
                }
                case "switcher" : {
                    group = 10;
                    break;
                }
            }
            paramsGraph.addNode(
                    host.getIp(),
                    group,
                    host.getVulnerabilityCount()/3
            );
            nameAndId.put(host.getIp(), counter);
            counter++;
        }
        for (Link link : network.getLinks()){
            paramsGraph.addLink(
                    nameAndId.get(link.getHostIpFrom()),
                    nameAndId.get(link.getHostIpTo()),
                    link.getChannelCpacity(),
                    link.getChannelLoad(),
                    link.getChannelValue(),
                    false
            );
        }
        return paramsGraph;
    }
}
