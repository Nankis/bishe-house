package com.lrs.bishe.service;

import com.lrs.bishe.entity.MsgBoard;
import java.util.List;

/**
 * (MsgBoard)表服务接口
 *
 * @author makejava
 * @since 2020-03-20 12:10:41
 */
public interface MsgBoardService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MsgBoard queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<MsgBoard> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param msgBoard 实例对象
     * @return 实例对象
     */
    MsgBoard insert(MsgBoard msgBoard);

    /**
     * 修改数据
     *
     * @param msgBoard 实例对象
     * @return 实例对象
     */
    MsgBoard update(MsgBoard msgBoard);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param msgBoard 实例对象
     * @return 对象列表
     */
    List<MsgBoard> queryAll(MsgBoard msgBoard);
}