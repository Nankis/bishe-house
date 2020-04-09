package com.lrs.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (HousePic)实体类
 *
 * @author makejava
 * @since 2020-03-16 15:20:16
 */
public class HousePic implements Serializable {
    private static final long serialVersionUID = -14545094743231494L;
    
    private Integer id;
    
    private Integer houseId;
    
    private String picUrl;
    
    private Date createdTime;
    
    private Date updatedTime;
    
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}