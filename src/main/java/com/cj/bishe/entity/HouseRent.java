package com.cj.bishe.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * (HouseRent)实体类
 *
 * @author makejava
 * @since 2020-03-16 15:19:56
 */
public class HouseRent implements Serializable {
    private static final long serialVersionUID = -75614905063901655L;

    private Integer houseId;

    private Integer userId;

    private Integer adminId;

    private String houseTitle;

    //租金
    private Double houseRent;

    //押金
    private Double housePledge;

    //押一付（1，2，3）
    private Integer houseMonths;

    private String houseAddress;

    private String houseShape;

    private String houseDirection;

    private String houseArea;

    private String houseFloor;

    private String houseDetail;

    //经纬度
    private String houseJwd;

    //是否被租
    private String houseIsrented;

    //封面图
    private String houseCoverpic;

    //详情图
    private String housePiclist;

    private Date createdTime;

    private Date updatedTime;

    private String status;

    private List<String> House_picList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getHouse_picList() {
        return House_picList;
    }

    public void setHouse_picList(List<String> house_picList) {
        House_picList = house_picList;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle(String houseTitle) {
        this.houseTitle = houseTitle;
    }

    public Double getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(Double houseRent) {
        this.houseRent = houseRent;
    }

    public Double getHousePledge() {
        return housePledge;
    }

    public void setHousePledge(Double housePledge) {
        this.housePledge = housePledge;
    }

    public Integer getHouseMonths() {
        return houseMonths;
    }

    public void setHouseMonths(Integer houseMonths) {
        this.houseMonths = houseMonths;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseShape() {
        return houseShape;
    }

    public void setHouseShape(String houseShape) {
        this.houseShape = houseShape;
    }

    public String getHouseDirection() {
        return houseDirection;
    }

    public void setHouseDirection(String houseDirection) {
        this.houseDirection = houseDirection;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(String houseFloor) {
        this.houseFloor = houseFloor;
    }

    public String getHouseDetail() {
        return houseDetail;
    }

    public void setHouseDetail(String houseDetail) {
        this.houseDetail = houseDetail;
    }

    public String getHouseJwd() {
        return houseJwd;
    }

    public void setHouseJwd(String houseJwd) {
        this.houseJwd = houseJwd;
    }

    public String getHouseIsrented() {
        return houseIsrented;
    }

    public void setHouseIsrented(String houseIsrented) {
        this.houseIsrented = houseIsrented;
    }

    public String getHouseCoverpic() {
        return houseCoverpic;
    }

    public void setHouseCoverpic(String houseCoverpic) {
        this.houseCoverpic = houseCoverpic;
    }

    public String getHousePiclist() {
        return housePiclist;
    }

    public void setHousePiclist(String housePiclist) {
        this.housePiclist = housePiclist;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HouseRent houseRent = (HouseRent) o;
        return Objects.equals(houseId, houseRent.houseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houseId);
    }
}