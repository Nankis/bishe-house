package com.cj.bishe.service;

import com.cj.bishe.entity.HousePic;

import java.util.List;

/**
 * (HousePic)表服务接口
 *
 * @author makejava
 * @since 2020-03-16 15:20:16
 */
public interface HousePicService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    HousePic queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<HousePic> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param housePic 实例对象
     * @return 实例对象
     */
    HousePic insert(HousePic housePic);

    /**
     * 修改数据
     *
     * @param housePic 实例对象
     * @return 实例对象
     */
    HousePic update(HousePic housePic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 查询对应条件的图片
     *
     * @param housePic
     * @return
     */
    List<HousePic> queryAll(HousePic housePic);
}