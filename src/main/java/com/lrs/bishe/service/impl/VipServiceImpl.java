package com.lrs.bishe.service.impl;

import com.lrs.bishe.entity.Vip;
import com.lrs.bishe.dao.VipDao;
import com.lrs.bishe.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Vip)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 20:19:49
 */
@Service("vipService")
public class VipServiceImpl implements VipService {
    @Resource
    private VipDao vipDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Vip queryById(Integer id) {
        return this.vipDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Vip> queryAllByLimit(int offset, int limit) {
        return this.vipDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Vip queryByUserId(Integer userId) {
        return vipDao.queryByUserId(userId);
    }

    /**
     * 新增数据
     *
     * @param vip 实例对象
     * @return 实例对象
     */
    @Override
    public Vip insert(Vip vip) {
        this.vipDao.insert(vip);
        return vip;
    }

    /**
     * 修改数据
     *
     * @param vip 实例对象
     * @return 实例对象
     */
    @Override
    public Vip update(Vip vip) {
        this.vipDao.update(vip);
        return this.queryById(vip.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.vipDao.deleteById(id) > 0;
    }
}