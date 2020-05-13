package com.tnpy.plantvehiclems.model.mysql;

import java.util.Date;

public class CarDriveRecord {
    private String id;

    private String carid;

    private Date cometime;

    private Date gotime;

    private String comerecorder;

    private String comerecorderid;

    private String gorecorder;

    private String gorecorderid;

    private String extd1;

    private String extd2;

    private String extd3;

    private String remark;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid == null ? null : carid.trim();
    }

    public Date getCometime() {
        return cometime;
    }

    public void setCometime(Date cometime) {
        this.cometime = cometime;
    }

    public Date getGotime() {
        return gotime;
    }

    public void setGotime(Date gotime) {
        this.gotime = gotime;
    }

    public String getComerecorder() {
        return comerecorder;
    }

    public void setComerecorder(String comerecorder) {
        this.comerecorder = comerecorder == null ? null : comerecorder.trim();
    }

    public String getComerecorderid() {
        return comerecorderid;
    }

    public void setComerecorderid(String comerecorderid) {
        this.comerecorderid = comerecorderid == null ? null : comerecorderid.trim();
    }

    public String getGorecorder() {
        return gorecorder;
    }

    public void setGorecorder(String gorecorder) {
        this.gorecorder = gorecorder == null ? null : gorecorder.trim();
    }

    public String getGorecorderid() {
        return gorecorderid;
    }

    public void setGorecorderid(String gorecorderid) {
        this.gorecorderid = gorecorderid == null ? null : gorecorderid.trim();
    }

    public String getExtd1() {
        return extd1;
    }

    public void setExtd1(String extd1) {
        this.extd1 = extd1 == null ? null : extd1.trim();
    }

    public String getExtd2() {
        return extd2;
    }

    public void setExtd2(String extd2) {
        this.extd2 = extd2 == null ? null : extd2.trim();
    }

    public String getExtd3() {
        return extd3;
    }

    public void setExtd3(String extd3) {
        this.extd3 = extd3 == null ? null : extd3.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}