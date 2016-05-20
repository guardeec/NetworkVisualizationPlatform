package data.networkdata;

/**
 * Created by Guardeec on 26.04.16.
 */
public class Host {
    private String type;
    private Float informationValue;
    private String ip;
    private String fqdn;
    private Float load;
    private Float vulnerabilityHighestLevel;
    private Integer vulnerabilityCount;
    private Float possibleDamage;

    public Host() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getInformationValue() {
        return informationValue;
    }

    public void setInformationValue(Float informationValue) {
        this.informationValue = informationValue;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public Float getLoad() {
        return load;
    }

    public void setLoad(Float load) {
        this.load = load;
    }

    public Float getVulnerabilityHighestLevel() {
        return vulnerabilityHighestLevel;
    }

    public void setVulnerabilityHighestLevel(Float vulnerabilityHighestLevel) {
        this.vulnerabilityHighestLevel = vulnerabilityHighestLevel;
    }

    public Integer getVulnerabilityCount() {
        return vulnerabilityCount;
    }

    public void setVulnerabilityCount(Integer vulnerabilityCount) {
        this.vulnerabilityCount = vulnerabilityCount;
    }

    public Float getPossibleDamage() {
        return possibleDamage;
    }

    public void setPossibleDamage(Float possibleDamage) {
        this.possibleDamage = possibleDamage;
    }
}
