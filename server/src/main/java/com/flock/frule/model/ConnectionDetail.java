package com.flock.frule.model;

public class ConnectionDetail {
    private String sourceID;
    private String targetID;

    public ConnectionDetail() {
    }

    public ConnectionDetail(String sourceID, String targetID) {
        this.sourceID = sourceID;
        this.targetID = targetID;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getTargetID() {
        return targetID;
    }

    public void setTargetID(String targetID) {
        this.targetID = targetID;
    }

    @Override
    public String toString() {
        return "ConnectionDetail{" +
                "sourceID='" + sourceID + '\'' +
                ", targetID='" + targetID + '\'' +
                '}';
    }
}
