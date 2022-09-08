package com.example.first_project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomResponse {

    @SerializedName("VersionID")
    @Expose
    private Integer versionID;
    @SerializedName("VersionName")
    @Expose
    private String versionName;
    @SerializedName("ForceUpdate")
    @Expose
    private Boolean forceUpdate;
    @SerializedName("ActiveInd")
    @Expose
    private Boolean activeInd;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;

    public Integer getVersionID() {
        return versionID;
    }

    public void setVersionID(Integer versionID) {
        this.versionID = versionID;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public Boolean getActiveInd() {
        return activeInd;
    }

    public void setActiveInd(Boolean activeInd) {
        this.activeInd = activeInd;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}