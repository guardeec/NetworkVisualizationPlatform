package data.rawdata.csvdata;

/**
 * Created by Guardeec on 27.04.16.
 */
public class Link {
    private String hostIpFrom;
    private String hostIpTo;
    private Integer channelCapacity;
    private Integer channelDamageCoast;
    private Float channelLoad;
    private Float channelValue;

    public String getHostIpFrom() {
        return hostIpFrom;
    }

    public void setHostIpFrom(String hostIpFrom) {
        this.hostIpFrom = hostIpFrom;
    }

    public String getHostIpTo() {
        return hostIpTo;
    }

    public void setHostIpTo(String hostIpTo) {
        this.hostIpTo = hostIpTo;
    }

    public Integer getChannelCapacity() {
        return channelCapacity;
    }

    public void setChannelCapacity(Integer channelCapacity) {
        this.channelCapacity = channelCapacity;
    }

    public Integer getChannelDamageCoast() {
        return channelDamageCoast;
    }

    public void setChannelDamageCoast(Integer channelDamageCoast) {
        this.channelDamageCoast = channelDamageCoast;
    }

    public Float getChannelLoad() {
        return channelLoad;
    }

    public void setChannelLoad(Float channelLoad) {
        this.channelLoad = channelLoad;
    }

    public Float getChannelValue() {
        return channelValue;
    }

    public void setChannelValue(Float channelValue) {
        this.channelValue = channelValue;
    }
}
