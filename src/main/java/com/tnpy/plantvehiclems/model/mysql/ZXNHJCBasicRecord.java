package com.tnpy.plantvehiclems.model.mysql;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-27 10:45
 */
public class ZXNHJCBasicRecord {
    private String dno;
    private String lock_date;
    private String smsg;
    private String emsg;
    private String dmsg;
    private String datasource;
    private String create_user;
    private String gmt_create;

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getLock_date() {
        return lock_date;
    }

    public void setLock_date(String lock_date) {
        this.lock_date = lock_date;
    }

    public String getSmsg() {
        return smsg;
    }

    public void setSmsg(String smsg) {
        this.smsg = smsg;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public String getDmsg() {
        return dmsg;
    }

    public void setDmsg(String dmsg) {
        this.dmsg = dmsg;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(String gmt_create) {
        this.gmt_create = gmt_create;
    }
}
