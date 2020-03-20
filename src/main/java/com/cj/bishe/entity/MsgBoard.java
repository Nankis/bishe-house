package com.cj.bishe.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (MsgBoard)实体类
 *
 * @author makejava
 * @since 2020-03-20 02:10:40
 */
public class MsgBoard implements Serializable {
    private static final long serialVersionUID = 498171323489932109L;
    
    private Integer id;
    
    private String user;
    
    private String msg;
    
    private Date pubTime;

    private String name;

    private String createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
    * 楼层备用
    */
    private Integer num;
    
    private Date createdTime;
    
    private Date modifiedTime;
    
    private Integer deleted;
    
    private Integer status;
    
    private Integer houseid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

}