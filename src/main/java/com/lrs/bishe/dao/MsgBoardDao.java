package com.lrs.bishe.dao;

import com.lrs.bishe.entity.MsgBoard;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (MsgBoard)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-20 12:10:41
 */
public interface MsgBoardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MsgBoard queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MsgBoard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param msgBoard 实例对象
     * @return 对象列表
     */
    List<MsgBoard> queryAll(MsgBoard msgBoard);

    /**
     * 新增数据
     *
     * @param msgBoard 实例对象
     * @return 影响行数
     */
    int insert(MsgBoard msgBoard);

    /**
     * 修改数据
     *
     * @param msgBoard 实例对象
     * @return 影响行数
     */
    int update(MsgBoard msgBoard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}