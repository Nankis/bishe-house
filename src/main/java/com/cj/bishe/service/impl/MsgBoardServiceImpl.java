package com.cj.bishe.service.impl;

import com.cj.bishe.entity.MsgBoard;
import com.cj.bishe.dao.MsgBoardDao;
import com.cj.bishe.service.MsgBoardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MsgBoard)表服务实现类
 *
 * @author makejava
 * @since 2020-03-20 02:10:41
 */
@Service("msgBoardService")
public class MsgBoardServiceImpl implements MsgBoardService {
    @Resource
    private MsgBoardDao msgBoardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MsgBoard queryById(Integer id) {
        return this.msgBoardDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<MsgBoard> queryAllByLimit(int offset, int limit) {
        return this.msgBoardDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param msgBoard 实例对象
     * @return 实例对象
     */
    @Override
    public MsgBoard insert(MsgBoard msgBoard) {
        this.msgBoardDao.insert(msgBoard);
        return msgBoard;
    }

    /**
     * 修改数据
     *
     * @param msgBoard 实例对象
     * @return 实例对象
     */
    @Override
    public MsgBoard update(MsgBoard msgBoard) {
        this.msgBoardDao.update(msgBoard);
        return this.queryById(msgBoard.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.msgBoardDao.deleteById(id) > 0;
    }

    @Override
    public List<MsgBoard> queryAll(MsgBoard msgBoard) {
        return msgBoardDao.queryAll(msgBoard);
    }
}