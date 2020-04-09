package com.lrs.bishe.service;

import com.lrs.bishe.entity.SubHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SubHouse)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 21:33:28
 */
public interface SubHouseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubHouse queryById(Integer id);

    /**
     * 查询时间范围内的预约house
     *
     * @return 对象列表
     */
    List<SubHouse> queryAllByTime(String startT, String endT);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param subHouse 实例对象
     * @return 对象列表
     */
    List<SubHouse> queryAll(SubHouse subHouse);

    /**
     * 查询时间范围内的预约house
     *
     * @return 对象列表
     */
    String queryHaveSubByTimeAndHouse(String startT, String endT,Integer houseId);

    /**
     * 查询指定idhouse开始时间大于等于 指定开始时间的预约
     *
     * @return 对象列表
     */
    List<SubHouse> queryAllByStartTimeAndHouse(@Param("startT") String startT, @Param("houseId") Integer houseId);

    /**
     * 查询指定idhouse开始时间小于等于 指定开始时间的预约
     *
     * @return 对象列表
     */
    List<SubHouse> queryAllByEndTimeAndHouse(@Param("endT") String endT,@Param("houseId") Integer houseId);


    /**
     * 查询指定idhouse开始时间大于等于 指定开始时间的预约 倒序排最大
     *
     * @return 对象列表
     */
    List<SubHouse> queryAllByMaxEndTimeAndHouse(@Param("startT") String startT,@Param("houseId") Integer houseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SubHouse> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param subHouse 实例对象
     * @return 实例对象
     */
    SubHouse insert(SubHouse subHouse);

    /**
     * 修改数据
     *
     * @param subHouse 实例对象
     * @return 实例对象
     */
    SubHouse update(SubHouse subHouse);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}