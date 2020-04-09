package com.lrs.bishe.dao;

import com.lrs.bishe.entity.HouseRent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (HouseRent)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 15:19:56
 */
public interface HouseRentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param houseId 主键
     * @return 实例对象
     */
    HouseRent queryById(Integer houseId);

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


    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HouseRent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 查询指定行数据包含全部数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HouseRent> queryAllByLimits(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过租金或者押金方式查询
     *
     * @param rent1
     * @param rent2
     * @param month1
     * @param month2
     * @return
     */
    List<HouseRent> queryAllByRentOrMonths(@Param("rent1") double rent1, @Param("rent2") double rent2,
                                           @Param("month1") int month1, @Param("month2") int month2);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param houseRent 实例对象
     * @return 对象列表
     */
    List<HouseRent> queryAll(HouseRent houseRent);

    /**
     * 通过标题,居室类型查询
     *
     * @param houseRent
     * @return
     */
    List<HouseRent> queryAllByConditions(HouseRent houseRent);



    /**
     * 新增数据
     *
     * @param houseRent 实例对象
     * @return 影响行数
     */
    Integer insert(HouseRent houseRent);

    /**
     * 修改数据
     *
     * @param houseRent 实例对象
     * @return 影响行数
     */
    int update(HouseRent houseRent);

    /**
     * 通过主键删除数据
     *
     * @param houseId 主键
     * @return 影响行数
     */
    int deleteById(Integer houseId);

}