package com.lrs.bishe.dao;

import com.lrs.bishe.entity.HousePic;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (HousePic)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-16 15:20:16
 */
public interface HousePicDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HousePic queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<HousePic> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param housePic 实例对象
     * @return 对象列表
     */
    List<HousePic> queryAll(HousePic housePic);

    /**
     * 新增数据
     *
     * @param housePic 实例对象
     * @return 影响行数
     */
    int insert(HousePic housePic);

    /**
     * 修改数据
     *
     * @param housePic 实例对象
     * @return 影响行数
     */
    int update(HousePic housePic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}