package com.lrs.bishe.service;

import com.lrs.bishe.entity.HouseRent;

import java.util.HashMap;
import java.util.List;

/**
 * (HouseRent)表服务接口
 *
 * @author makejava
 * @since 2020-03-16 15:19:56
 */
public interface HouseRentService {

    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    HouseRent queryById(Integer houseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HouseRent> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HouseRent> queryAllByLimits(int offset, int limit);

    /**
     * 通过租金或者押金方式查询
     *
     * @param rent1
     * @param rent2
     * @param month1
     * @param month2
     * @return
     */
    List<HouseRent> queryAllByRentOrMonths(double rent1, double rent2, int month1, int month2);

    /**
     * 新增数据
     *
     * @param houseRent 实例对象
     * @return 实例对象
     */
    HouseRent insert(HouseRent houseRent);

    /**
     * 修改数据
     *
     * @param houseRent 实例对象
     * @return 实例对象
     */
    HouseRent update(HouseRent houseRent);

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer houseId);

    void getPic(List<HouseRent> houseRents);

    HashMap<String, Object> getDetailHouse(int houseId);

    /**
     * 通过标题，居室类型查询
     *
     * @param houseRent
     * @return
     */
    List<HouseRent> queryAllByConditions(HouseRent houseRent);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param houseRent 实例对象
     * @return 对象列表
     */
    List<HouseRent> queryAll(HouseRent houseRent);

    /**
     * 解析参数，并且存入数据库
     * 发布房源
     *
     * @param str
     * @return
     */
    boolean pubHouse(String str);

    /**
     * 通过ID查询单条数据 已租到的
     *
     * @param houseId 主键
     * @return 实例对象
     */
    HouseRent queryByIdY(Integer houseId);


    /**
     * 通过ID查询单条数据 已租到的
     *
     * @param houseId 主键
     * @return 实例对象
     */
    HouseRent queryByIdISY(Integer houseId);

}