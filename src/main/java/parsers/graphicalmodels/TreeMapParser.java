package parsers.graphicalmodels;

import data.graphicalmodelsdata.ParamsTreeMap;
import data.networkdata.Host;
import data.networkdata.Link;
import data.networkdata.Network;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 27.04.16.
 */
public class TreeMapParser {
    public ParamsTreeMap parseToMap(Network network){
        List<ParamsTreeMap> paramsTreeMapList = new LinkedList<>();
        for (Host host : network.getHosts()){
            ParamsTreeMap element = new ParamsTreeMap(host.getIp());
            element.setSize(host.getVulnerabilityCount());
            paramsTreeMapList.add(element);
        }
        for (Link link : network.getLinks()){
            ParamsTreeMap daddy = null;
            ParamsTreeMap baby = null;
            for (ParamsTreeMap element : paramsTreeMapList){
                if (element.getName().compareTo(link.getHostIpFrom())==0){
                    daddy = element;
                }
                if (element.getName().compareTo(link.getHostIpTo())==0){
                    baby = element;
                }
            }
            daddy.addChildren(baby);
        }
        ParamsTreeMap root = new ParamsTreeMap("");
        root.setSize(0);
        for (ParamsTreeMap paramsTreeMap : paramsTreeMapList){
            boolean noParents = true;
            for (ParamsTreeMap element : paramsTreeMapList){
                if (element.getChildrens()!=null) {
                    if (element.getChildrens().contains(paramsTreeMap)) {
                        noParents = false;
                    }
                }
            }
            if (noParents){
                return paramsTreeMap;
            }
        }
        return null;
    }
}


