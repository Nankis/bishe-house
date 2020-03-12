package com.cj.bishe.dao;

import com.cj.bishe.entity.House;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (House)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-11 21:13:11
 */
public interface HouseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    House queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<House> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param house 实例对象
     * @return 对象列表
     */
    List<House> queryAll(House house);

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int insert(House house);

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 影响行数
     */
    int update(House house);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}