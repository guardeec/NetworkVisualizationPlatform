package data.networkdata;

/**
 * Created by Guardeec on 26.04.16.
 */
public class Link {
    private Integer channelCpacity;
    private Float channelLoad;
    private Float channelValue;
    private Integer channelDamageCoast;
    private String hostIpFrom;
    private String hostIpTo;
    public Link() {
    }

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

    public Integer getChannelCpacity() {
        return channelCpacity;
    }

    public void setChannelCpacity(Integer channelCpacity) {
        this.channelCpacity = channelCpacity;
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

    public Integer getChannelDamageCoast() {
        return channelDamageCoast;
    }

    public void setChannelDamageCoast(Integer channelDamageCoast) {
        this.channelDamageCoast = channelDamageCoast;
    }
}
