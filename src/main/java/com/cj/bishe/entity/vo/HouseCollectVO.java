package com.cj.bishe.entity.vo;

import com.cj.bishe.entity.House;

import java.io.Serializable;
import java.util.List;

/**
 * 用于给前端显示用户收藏房子数据的类
 */
public class HouseCollectVO implements Serializable {
    private Integer id;
    /**
     * 重新组合的地址
     */
    private String address;

    private Double price;

    private String type;

    /**
     * 面积
     */
    private String area;

    private String img;
    /**
     * 被收藏数
     */
    private String houseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
