package com.cj.bishe.service.impl;

import com.cj.bishe.entity.HousePic;
import com.cj.bishe.entity.HouseRent;
import com.cj.bishe.dao.HouseRentDao;
import com.cj.bishe.entity.User;
import com.cj.bishe.service.HousePicService;
import com.cj.bishe.service.HouseRentService;
import com.cj.bishe.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (HouseRent)表服务实现类
 *
 * @author makejava
 * @since 2020-03-16 15:19:56
 */
@Service("houseRentService")
public class HouseRentServiceImpl implements HouseRentService {
    @Resource
    private HouseRentDao houseRentDao;

    @Resource
    private HousePicService housePicService;

    @Resource
    private UserService userService;

    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    @Override
    public HouseRent queryById(Integer houseId) {
        return this.houseRentDao.queryById(houseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<HouseRent> queryAllByLimit(int offset, int limit) {
        return this.houseRentDao.queryAllByLimit(offset, limit);
    }

    @Override
    public List<HouseRent> queryAllByRentOrMonths(double rent1, double rent2, int month1, int month2) {
        return houseRentDao.queryAllByRentOrMonths(rent1, rent2, month1, month2);
    }

    /**
     * 新增数据
     *
     * @param houseRent 实例对象
     * @return 实例对象
     */
    @Override
    public HouseRent insert(HouseRent houseRent) {
        this.houseRentDao.insert(houseRent);
        return houseRent;
    }

    /**
     * 修改数据
     *
     * @param houseRent 实例对象
     * @return 实例对象
     */
    @Override
    public HouseRent update(HouseRent houseRent) {
        this.houseRentDao.update(houseRent);
        return this.queryById(houseRent.getHouseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer houseId) {
        return this.houseRentDao.deleteById(houseId) > 0;
    }

    /**
     * 从房屋图片表里通过房屋id获取对应图片
     *
     * @param houseRents
     */
    @Override
    public void getPic(List<HouseRent> houseRents) {
        for (HouseRent houseRent : houseRents) {
            Integer houseId = houseRent.getHouseId();

            HousePic housePic = new HousePic();
            housePic.setHouseId(houseId);
            List<HousePic> housePics = housePicService.queryAll(housePic);
            List<String> urlList = new ArrayList<>();

            for (HousePic v : housePics) {
                urlList.add(v.getPicUrl());
            }

            houseRent.setHouse_picList(urlList);
        }
    }

    @Override
    public HashMap<String, Object> getDetailHouse(int houseId) {
        HouseRent house = houseRentDao.queryById(houseId);
        HashMap<String, Object> Data = new HashMap<>();
        Data.put("House", house);
        User adminUser = userService.queryById(house.getAdminId());
        Data.put("Admin", adminUser);
        //查询对应house的全部的housePic
        HousePic housePic = new HousePic();
        housePic.setHouseId(houseId);
        List<HousePic> housePics = housePicService.queryAll(housePic);
        Map<String, Object> HousePicture = new HashMap<>();
        HousePicture.put("TotalCount", housePics.size());
        HousePicture.put("_Items", housePics);
        Data.put("HousePicture", HousePicture);
        return Data;
    }

    @Override
    public List<HouseRent> queryAllByConditions(HouseRent houseRent) {
        return houseRentDao.queryAllByConditions(houseRent);
    }

    @Override
    public List<HouseRent> queryAll(HouseRent houseRent) {
        return houseRentDao.queryAll(houseRent);
    }


}