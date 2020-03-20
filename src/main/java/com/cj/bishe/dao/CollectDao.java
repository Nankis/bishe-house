package com.cj.bishe.dao;

import com.cj.bishe.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Collect)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-12 17:20:36
 */
public interface CollectDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collect queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Collect> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param collect 实例对象
     * @return 对象列表
     */
    List<Collect> queryAll(Collect collect);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int insert(Collect collect);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 影响行数
     */
    int update(Collect collect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 通过用户id和houseid删除收藏的房子
     *
     * @param collect 实体类
     * @return 影响行数
     */
    int deleteByUserIdAndHouseId(Collect collect);

    /**
     * 查询是用户是否收藏过该房子
     *
     * @param collect
     * @return
     */
    Collect queryByUserIdAndHouseId(Collect collect);

}