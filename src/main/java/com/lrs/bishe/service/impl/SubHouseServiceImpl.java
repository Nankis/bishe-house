package com.lrs.bishe.service.impl;

import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.IntervalUtil;
import com.lrs.bishe.entity.SubHouse;
import com.lrs.bishe.dao.SubHouseDao;
import com.lrs.bishe.service.SubHouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (SubHouse)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 21:33:28
 */
@Service("subHouseService")
public class SubHouseServiceImpl implements SubHouseService {
    @Resource
    private SubHouseDao subHouseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubHouse queryById(Integer id) {
        return this.subHouseDao.queryById(id);
    }

    @Override
    public List<SubHouse> queryAllByTime(String startT, String endT) {
        return subHouseDao.queryAllByTime(startT, endT);
    }

    @Override
    public List<SubHouse> queryAll(SubHouse subHouse) {
        return subHouseDao.queryAll(subHouse);
    }

    @Override
    public String queryHaveSubByTimeAndHouse(String startT, String endT, Integer houseId) {
        SubHouse subHouse = new SubHouse();
        subHouse.setHouseid(houseId);
        //查找该id自习室的 所有的预约  （比现在的时间大）
        //7~11   10~12  ，最小值是现在   最大值是该id有效的最大值
        String res = null;
        List<SubHouse> subList = subHouseDao.queryAllByStartTimeAndHouse(DateUtils.dateToStringFormat(new Date()), houseId);
        //查询结束时间小于等于endT
        List<SubHouse> subList2 = subHouseDao.queryAllByEndTimeAndHouse(endT, houseId);
        //去重取并集
        subList.removeAll(subList2);
        subList.addAll(subList2);

        boolean sub;
        if (subList.size() > 0) {
            for (SubHouse v : subList) {
                sub = checkHaveSub(startT, endT, v);
                if (sub) {
                    res = DateUtils.dateToStringFormat(v.getStartTime()) + " ~ " + DateUtils.dateToStringFormat(v.getEndTime());
                    //如果该时间段有预约则直接跳出
                    break;
                }
            }
        }
        return res;
    }

    private boolean checkHaveSub(String start, String end, SubHouse subHouse) {
        long startT = DateUtils.strToDateLong(start).getTime();
        long endT = DateUtils.strToDateLong(end).getTime();
        //7~11   10 ~ 12   开始时间在 区间(subStartTime,subEndTime) ===>true
        String startP = "(" + subHouse.getStartTime().getTime() + "," + subHouse.getEndTime().getTime() + ")";
        if (IntervalUtil.isInTheInterval(String.valueOf(startT), startP)) {
            //预定开始的时间在已有的预约时间段内
            return true;
        }
        //结束时间在 区间(subStartTime,subEndTime) ===>true
        String endP = "(" + subHouse.getStartTime().getTime() + "," + subHouse.getEndTime().getTime() + ")";
        if (IntervalUtil.isInTheInterval(String.valueOf(endT), endP)) {
            //预定结束的时间在已有的预约时间段内
            return true;
        }
        return false;
    }

    @Override
    public List<SubHouse> queryAllByStartTimeAndHouse(String startT, Integer houseId) {
        return subHouseDao.queryAllByStartTimeAndHouse(startT, houseId);
    }

    @Override
    public List<SubHouse> queryAllByEndTimeAndHouse(String endT, Integer houseId) {
        return subHouseDao.queryAllByEndTimeAndHouse(endT, houseId);
    }

    @Override
    public List<SubHouse> queryAllByMaxEndTimeAndHouse(String startT, Integer houseId) {
        return subHouseDao.queryAllByMaxEndTimeAndHouse(startT, houseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SubHouse> queryAllByLimit(int offset, int limit) {
        return this.subHouseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param subHouse 实例对象
     * @return 实例对象
     */
    @Override
    public SubHouse insert(SubHouse subHouse) {
        this.subHouseDao.insert(subHouse);
        return subHouse;
    }

    /**
     * 修改数据
     *
     * @param subHouse 实例对象
     * @return 实例对象
     */
    @Override
    public SubHouse update(SubHouse subHouse) {
        this.subHouseDao.update(subHouse);
        return this.queryById(subHouse.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.subHouseDao.deleteById(id) > 0;
    }
}