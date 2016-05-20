package parsers.graphicalmodels;

import data.graphicalmodelsdata.ParamsMatrix;
import data.networkdata.Host;
import data.networkdata.Link;
import data.networkdata.Network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guardeec on 27.04.16.
 */
public class MatrixParser {
    public ParamsMatrix parseToMatrix(Network network){
        ParamsMatrix paramsMatrix = new ParamsMatrix();
        Map<String, Integer> nameAndId = new HashMap<>();
        int counter = 0;
        for (Host host : network.getHosts()){
            paramsMatrix.addNode(host.getIp());
            nameAndId.put(host.getIp(), counter);
            counter++;
        }
        for (Link link : network.getLinks()){
            paramsMatrix.addLink(
                    nameAndId.get(link.getHostIpFrom()),
                    nameAndId.get(link.getHostIpTo()),
                    link.getChannelLoad(),
                    link.getChannelValue()
            );
        }
        return paramsMatrix;
    }
}
