package com.lrs.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SubHouse)实体类
 *
 * @author makejava
 * @since 2020-03-27 21:33:28
 */
public class SubHouse implements Serializable {
    private static final long serialVersionUID = 104771189039752959L;
    
    private Integer id;
    
    private Integer userid;
    
    private Integer houseid;
    
    private Date startTime;
    
    private Date endTime;
    
    private Date createdTime;
    
    private Date updatedTime;
    
    private String deleted;
    
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

}