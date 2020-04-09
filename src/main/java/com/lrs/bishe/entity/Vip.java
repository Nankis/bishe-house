package com.lrs.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Vip)实体类
 *
 * @author makejava
 * @since 2020-03-27 20:19:49
 */
public class Vip implements Serializable {
    private static final long serialVersionUID = -81062803819517092L;
    
    private Integer id;
    
    private Integer userid;
    
    private String viptype;
    
    private Date createdTime;
    
    private Date updatedTime;
    
    private String status;
    
    private String deleted;
    /**
    * 过期时间
    */
    private Date expirTime;


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

    public String getViptype() {
        return viptype;
    }

    public void setViptype(String viptype) {
        this.viptype = viptype;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Date getExpirTime() {
        return expirTime;
    }

    public void setExpirTime(Date expirTime) {
        this.expirTime = expirTime;
    }

}