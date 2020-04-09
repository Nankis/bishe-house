package com.lrs.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Collect)实体类
 *
 * @author makejava
 * @since 2020-03-12 17:20:33
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = 882711966994732677L;
    
    private Integer id;
    
    private Integer userId;
    
    private Integer houseId;
    
    private Date createdTime;
    
    private Date modifiedTime;
    
    private String deleted;
    
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
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