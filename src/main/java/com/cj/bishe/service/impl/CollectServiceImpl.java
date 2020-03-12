package com.cj.bishe.service.impl;

import com.cj.bishe.entity.Collect;
import com.cj.bishe.dao.CollectDao;
import com.cj.bishe.service.CollectService;
import com.cj.bishe.entity.vo.HouseCollectVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Collect)表服务实现类
 *
 * @author makejava
 * @since 2020-03-12 17:20:37
 */
@Service("collectService")
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectDao collectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Collect queryById(Integer id) {
        return this.collectDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Collect> queryAllByLimit(int offset, int limit) {
        return this.collectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect insert(Collect collect) {
        this.collectDao.insert(collect);
        return collect;
    }

    /**
     * 修改数据
     *
     * @param collect 实例对象
     * @return 实例对象
     */
    @Override
    public Collect update(Collect collect) {
        this.collectDao.update(collect);
        return this.queryById(collect.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.collectDao.deleteById(id) > 0;
    }


    @Override
    public List<Collect> queryAll(Collect collect) {
        return collectDao.queryAll(collect);
    }

    /**
     * 查询用户是否收藏过该房子
     *
     * @param userId
     * @param houseId
     * @return
     */
    @Override
    public Collect queryByUserIdAndHouseId(int userId, int houseId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setHouseId(houseId);
        return collectDao.queryByUserIdAndHouseId(collect);
    }

}