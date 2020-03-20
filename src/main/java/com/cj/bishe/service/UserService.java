package com.cj.bishe.service;

import com.cj.bishe.entity.HouseRent;
import com.cj.bishe.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-03-11 17:53:29
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 通过username查询单条数据
     *
     * @param userName
     * @return
     */
    User queryByUserName(String userName);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过username修改用户信息
     *
     * @param user
     * @return
     */
    User updateUserByUserName(User user);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 获取用户当前收藏的房子
     *
     * @param userId
     * @return
     */
    List<HouseRent> getUserCollect(Integer userId);

    /**
     * 收藏或取消收藏房子
     *
     * @param userId
     * @param houseId
     */
    boolean collectHouse(int userId, int houseId);

}