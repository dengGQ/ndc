package com.ndc.channel.flight.xmlBean.flightSearch.response;

public class RemarkText {
    private String cabinAv;
    private String comment;
    private String ei;
    private String eiComment;
    private Long maxTime;
    private String maxTimeFlag;
    private String maxTimeUnit;
    private Long minTime;
    private String minTimeFlag;
    private String minTimeUnit;
    private String noShowFlag;

    /**
     * 0-起飞前 1-起飞后
     */
    private String timeFlag;
    /**
     * 0-使用前 1-使用后
     */
    private String useFlag;

    public String getCabinAv() {
        return cabinAv;
    }

    public void setCabinAv(String cabinAv) {
        this.cabinAv = cabinAv;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEi() {
        return ei;
    }

    public void setEi(String ei) {
        this.ei = ei;
    }

    public String getEiComment() {
        return eiComment;
    }

    public void setEiComment(String eiComment) {
        this.eiComment = eiComment;
    }

    public String getMaxTimeFlag() {
        return maxTimeFlag;
    }

    public void setMaxTimeFlag(String maxTimeFlag) {
        this.maxTimeFlag = maxTimeFlag;
    }

    public String getMaxTimeUnit() {
        return maxTimeUnit;
    }

    public void setMaxTimeUnit(String maxTimeUnit) {
        this.maxTimeUnit = maxTimeUnit;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public String getMinTimeFlag() {
        return minTimeFlag;
    }

    public void setMinTimeFlag(String minTimeFlag) {
        this.minTimeFlag = minTimeFlag;
    }

    public String getMinTimeUnit() {
        return minTimeUnit;
    }

    public void setMinTimeUnit(String minTimeUnit) {
        this.minTimeUnit = minTimeUnit;
    }

    public String getNoShowFlag() {
        return noShowFlag;
    }

    public void setNoShowFlag(String noShowFlag) {
        this.noShowFlag = noShowFlag;
    }

    public String getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(String timeFlag) {
        this.timeFlag = timeFlag;
    }

    public String getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(String useFlag) {
        this.useFlag = useFlag;
    }
}
