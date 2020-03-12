package com.cj.bishe.controller;

import com.cj.bishe.common.HttpResult;
import com.cj.bishe.entity.House;
import com.cj.bishe.enums.ResultMsgEnum;
import com.cj.bishe.service.HouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (House)表控制层
 *
 * @author makejava
 * @since 2020-03-11 21:13:11
 */
@RestController
@RequestMapping("house")
public class HouseController {
    /**
     * 服务对象
     */
    @Resource
    private HouseService houseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getHouseById")
    public HttpResult getHouseById(Integer id) {
        House house = houseService.queryById(id);
        if (house==null){
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_HOUSE);
        }
        return HttpResult.success(house);
    }

}