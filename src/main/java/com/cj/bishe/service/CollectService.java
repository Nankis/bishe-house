package com.cj.bishe.service;

import com.cj.bishe.entity.Collect;
import com.cj.bishe.entity.vo.HouseCollectVO;

import java.util.List;

/**
 * (Collect)表服务接口
 *
 * @author makejava
 * @since 2020-03-12 17:20:37
 */
public interface CollectService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Collect queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Collect> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect insert(Collect collect);

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    Collect update(Collect collect);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过传入的collect查询所有符合条件的收藏值
     *
     * @param collect
     * @return
     */
    List<Collect> queryAll(Collect collect);

    /**
     * 查询用户是否收藏过该房子
     *
     * @param userId
     * @param houseId
     * @return
     */
    Collect queryByUserIdAndHouseId(int userId, int houseId);

    /**
     * 通过用户id和houseid删除收藏的房子
     *
     * @param collect 实体类
     * @return 影响行数
     */
    int deleteByUserIdAndHouseId(Collect collect);

}