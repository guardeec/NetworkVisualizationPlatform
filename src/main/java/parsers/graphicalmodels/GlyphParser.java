package parsers.graphicalmodels;

import data.graphicalmodelsdata.ParamsGlyph;
import data.graphicalmodelsdata.methods.Color;
import data.networkdata.Host;
import data.networkdata.Link;
import data.networkdata.Network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guardeec on 27.04.16.
 */
public class GlyphParser {
    public ParamsGlyph parseToGlyph(Network network){
        ParamsGlyph paramsGlyph = new ParamsGlyph();
        //расчет средних показателей
        float averageVulnerabilityHighestLevel = 0;
        float averagePossibleDamage = 0;
        float averageInformationValue = 0;
        float averageLoad = 0;
        for (Host host : network.getHosts()){
            averageVulnerabilityHighestLevel += host.getVulnerabilityHighestLevel();
            averagePossibleDamage += host.getPossibleDamage();
            averageInformationValue += host.getInformationValue();
            averageLoad += host.getLoad();
        }
        averageVulnerabilityHighestLevel = averageVulnerabilityHighestLevel / network.getHosts().size();
        averagePossibleDamage = averagePossibleDamage / network.getHosts().size();
        averageInformationValue = averageInformationValue / network.getHosts().size();
        averageLoad = averageLoad / network.getHosts().size();
        //заносим в структуру визуализации
        Map<String, Integer> nameAndId = new HashMap<>();
        int counter = 0;
        for (Host host : network.getHosts()){
            paramsGlyph.addNode(
                    host.getIp(),
                    host.getVulnerabilityCount()/3,
                    new int[]{
                            Color.getColorFromFloatToInteger(host.getVulnerabilityHighestLevel()/10),
                            Color.getColorFromFloatToInteger(host.getPossibleDamage()),
                            Color.getColorFromFloatToInteger(host.getInformationValue()),
                            Color.getColorFromFloatToInteger(host.getLoad())
                    },
                    new int[]{
                            Color.getColorFromFloatToInteger(averageVulnerabilityHighestLevel/10),
                            Color.getColorFromFloatToInteger(averagePossibleDamage),
                            Color.getColorFromFloatToInteger(averageInformationValue),
                            Color.getColorFromFloatToInteger(averageLoad)
                    }
                    );
            nameAndId.put(host.getIp(), counter);
            counter++;
        }
        for (Link link : network.getLinks()){
            paramsGlyph.addLink(
                    nameAndId.get(link.getHostIpFrom()),
                    nameAndId.get(link.getHostIpTo()),
                    link.getChannelCpacity(),
                    link.getChannelLoad(),
                    link.getChannelValue()
            );
        }
        return paramsGlyph;
    }
}
