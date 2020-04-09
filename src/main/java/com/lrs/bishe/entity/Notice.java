package com.lrs.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Notice)实体类
 *
 * @author makejava
 * @since 2020-03-27 00:19:05
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 387401367902214840L;
    
    private Integer id;
    
    private String contenxt;
    
    private Date createdTime;
    
    private Date updatedTime;
    
    private String deleted;
    
    private String status;
    
    private Integer userid;
    
    private String title;

    private String pubTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenxt() {
        return contenxt;
    }

    public void setContenxt(String contenxt) {
        this.contenxt = contenxt;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}