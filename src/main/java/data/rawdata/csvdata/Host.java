package data.rawdata.csvdata;

/**
 * Created by Guardeec on 10.05.16.
 */
public class Host {
    private Float informationValue;
    private Float load;
    private Float possibleDamage;
    private String type;
    //
    private String hostIp;
    private String fqdn;

    public Host() {
    }

    public Float getInformationValue() {
        return informationValue;
    }

    public void setInformationValue(Float informationValue) {
        this.informationValue = informationValue;
    }

    public Float getLoad() {
        return load;
    }

    public void setLoad(Float load) {
        this.load = load;
    }

    public Float getPossibleDamage() {
        return possibleDamage;
    }

    public void setPossibleDamage(Float possibleDamage) {
        this.possibleDamage = possibleDamage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }
}
