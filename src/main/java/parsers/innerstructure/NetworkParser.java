package parsers.innerstructure;

import data.networkdata.Network;
import data.rawdata.csvdata.CsvData;
import data.rawdata.csvdata.Link;
import data.rawdata.maxpatroldata.Host;
import data.rawdata.maxpatroldata.MaxPatrolData;

import java.util.Date;

/**
 * Created by Guardeec on 28.04.16.
 */
public class NetworkParser {
    public Network parseToInnerStructure(MaxPatrolData maxPatrolData, CsvData csvData, String name, String info){
        //приводим к внутренней структуре
        //тут задаем метаинформацию
        Network network = new Network(name, new Date(), info);
        //делаем для хостов
        for (Host maxPatrolElement : maxPatrolData.getHosts()){
            data.networkdata.Host host = new data.networkdata.Host();
            //это инфа с макспатрола
            host.setFqdn(maxPatrolElement.getFqdn());
            host.setIp(maxPatrolElement.getHostIp());
            host.setVulnerabilityCount(maxPatrolElement.getVulnerIds().size());
            host.setVulnerabilityHighestLevel(maxPatrolElement.getHighestVulnerabilityBaseScore(maxPatrolData.getVulnerabilityList()));

            //это инфа с цсв
            data.rawdata.csvdata.Host csvHost = csvData.getHostByKeys(maxPatrolElement.getHostIp(), maxPatrolElement.getFqdn());
            host.setInformationValue(csvHost.getInformationValue());
            host.setLoad(csvHost.getLoad());
            host.setPossibleDamage(csvHost.getPossibleDamage());
            host.setType(csvHost.getType());

            network.setHost(host);
        }

        //делаем для линков - вся инфа с цсв
        for (Link csvElement : csvData.getLinks()){
            data.networkdata.Link link = new data.networkdata.Link();
            link.setChannelCpacity(csvElement.getChannelCapacity());
            link.setChannelDamageCoast(csvElement.getChannelDamageCoast());
            link.setChannelLoad(csvElement.getChannelLoad());
            link.setChannelValue(csvElement.getChannelValue());
            link.setHostIpFrom(csvElement.getHostIpFrom());
            link.setHostIpTo(csvElement.getHostIpTo());

            network.setLink(link);
        }

        return network;
    }
}
