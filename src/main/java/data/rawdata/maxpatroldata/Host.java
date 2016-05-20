package data.rawdata.maxpatroldata;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 27.03.16.
 */
public class Host {
    private String hostIp;
    private String startTime;
    private Integer task;
    private Integer hostUid;
    private Integer scan;
    private String fqdn;
    private String stopTime;
    private String primary;
    private Integer exitcode;
    private Integer scanStatus;
    private String netbios;

    private String qualifierRule;
    private String scannerVersion;

    private List<Integer> vulnerIds = new LinkedList<>();

    public String getDataAsString(List<Vulnerability> vulnerabilities){
        String data = "";
        data += "hostIp="+hostIp+"\n";
        data += "startTime="+startTime+"\n";
        data += "task="+task+"\n";
        data += "hostUid="+hostUid+"\n";
        data += "scan="+scan+"\n";
        data += "fqdn="+fqdn+"\n";
        data += "stopTime="+stopTime+"\n";
        data += "primary="+primary+"\n";
        data += "exitCode="+exitcode+"\n";
        data += "scanStatus="+scanStatus+"\n";
        data += "netbios="+netbios+"\n";
        data += "qualifierRule="+qualifierRule+"\n";
        data += "scannerVersion="+scannerVersion+"\n";
        data += "Vulnerabilitys:\n";
        for (Vulnerability vulnerability : vulnerabilities){
            data += "   vulnerability Name="+vulnerability.getId().toString()+"\n";
        }

        return data;
    }

    public List<Vulnerability> getVulnerabilitys(List<Vulnerability> allVulnerabilitys){
        List<Vulnerability> vulnerabilities = new LinkedList<>();
        for (Vulnerability vulnerability : allVulnerabilitys){
            if (vulnerIds.contains(vulnerability.getId())){
                vulnerabilities.add(vulnerability);
            }
        }
        return vulnerabilities;
    }

    public Float getHighestVulnerabilityBaseScore(List<Vulnerability> allVulnerabilitys){
        float baseScore = 0;
        List<Vulnerability> vulnerabilities = getVulnerabilitys(allVulnerabilitys);
        for (Vulnerability vulnerability : vulnerabilities){
            if (vulnerability.getBaseScore()!=null){
                if (vulnerability.getBaseScore()>baseScore){
                    baseScore = vulnerability.getBaseScore();
                }
            }
        }
        return baseScore;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getHostUid() {
        return hostUid;
    }

    public void setHostUid(Integer hostUid) {
        this.hostUid = hostUid;
    }

    public Integer getScan() {
        return scan;
    }

    public void setScan(Integer scan) {
        this.scan = scan;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public Integer getExitcode() {
        return exitcode;
    }

    public void setExitcode(Integer exitcode) {
        this.exitcode = exitcode;
    }

    public Integer getScanStatus() {
        return scanStatus;
    }

    public void setScanStatus(Integer scanStatus) {
        this.scanStatus = scanStatus;
    }

    public String getNetbios() {
        return netbios;
    }

    public void setNetbios(String netbios) {
        this.netbios = netbios;
    }

    public String getQualifierRule() {
        return qualifierRule;
    }

    public void setQualifierRule(String qualifierRule) {
        this.qualifierRule = qualifierRule;
    }

    public String getScannerVersion() {
        return scannerVersion;
    }

    public void setScannerVersion(String scannerVersion) {
        this.scannerVersion = scannerVersion;
    }

    public List<Integer> getVulnerIds() {
        return vulnerIds;
    }

    public void setVulnerIds(List<Integer> vulnerIds) {
        this.vulnerIds = vulnerIds;
    }
}
