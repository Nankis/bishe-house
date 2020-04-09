package com.lrs.bishe.service.impl;

import com.lrs.bishe.entity.Collect;
import com.lrs.bishe.entity.HouseRent;
import com.lrs.bishe.entity.User;
import com.lrs.bishe.dao.UserDao;
import com.lrs.bishe.service.CollectService;
import com.lrs.bishe.service.HouseRentService;
import com.lrs.bishe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-03-11 17:53:29
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;

    @Resource
    private CollectService collectService;

    @Resource
    private HouseRentService houseRentService;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    @Override
    public User queryByUserName(String userName) {
        return this.userDao.queryByUserName(userName);
    }


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @Override
    public User updateUserByUserName(User user) {
        this.userDao.updateUserByUserName(user);
        return this.queryByUserName(user.getUsername());
    }

    @Override
    public List<User> queryAll(User user) {
        return userDao.queryAll(user);
    }

    /**
     * 获取用户当前收藏的房子
     *
     * @param userId
     * @return
     */

    @Override
    public List<HouseRent> getUserCollect(Integer userId) {
        Collect collect = new Collect();
        List<HouseRent> resHouse = new ArrayList<>();
        collect.setUserId(userId);
        List<Collect> collects = collectService.queryAll(collect);
        for (Collect v : collects) {
            //获取该用户收藏的所有house
            resHouse.add(houseRentService.queryById(v.getHouseId()));
        }
        return resHouse;
    }

    /**
     * 收藏或取消收藏用户指定的房子
     *
     * @param userId
     * @param houseId
     * @return
     */
    @Transactional
    @Override
    public boolean collectHouse(int userId, int houseId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setHouseId(houseId);
        Collect isCollected = collectService.queryByUserIdAndHouseId(userId, houseId);
        if (isCollected == null) {
            //未被收藏过，则重新收藏
            collectService.insert(collect);
            logger.info("收藏成功" + "userId:" + userId + "houseId:" + houseId);
        } else {
            //收藏过，则物理删除
            collectService.deleteById(isCollected.getId());
            logger.info("取消收藏成功" + "userId:" + userId + "houseId:" + houseId);
        }
        return true;
    }
}