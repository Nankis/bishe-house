package com.lrs.bishe.service;

import com.lrs.bishe.entity.Vip;
import java.util.List;

/**
 * (Vip)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 20:19:49
 */
public interface VipService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Vip queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Vip> queryAllByLimit(int offset, int limit);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Vip queryByUserId(Integer id);

    /**
     * 新增数据
     *
     * @param vip 实例对象
     * @return 实例对象
     */
    Vip insert(Vip vip);

    /**
     * 修改数据
     *
     * @param vip 实例对象
     * @return 实例对象
     */
    Vip update(Vip vip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}