package com.cj.bishe.service.impl;

import com.cj.bishe.entity.House;
import com.cj.bishe.dao.HouseDao;
import com.cj.bishe.entity.User;
import com.cj.bishe.service.HouseService;
import com.cj.bishe.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2020-03-11 21:13:11
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Resource
    private HouseDao houseDao;

    @Resource
    private UserService userService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public House queryById(Integer id) {
        return this.houseDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<House> queryAllByLimit(int offset, int limit) {
        return this.houseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House insert(House house) {
        this.houseDao.insert(house);
        return house;
    }

    /**
     * 修改数据
     *
     * @param house 实例对象
     * @return 实例对象
     */
    @Override
    public House update(House house) {
        this.houseDao.update(house);
        return this.queryById(house.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.houseDao.deleteById(id) > 0;
    }


}