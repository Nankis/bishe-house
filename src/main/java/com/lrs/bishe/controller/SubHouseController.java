package com.lrs.bishe.controller;

import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.HttpResult;
import com.lrs.bishe.entity.SubHouse;
import com.lrs.bishe.service.SubHouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (SubHouse)表控制层
 *
 * @author makejava
 * @since 2020-03-27 21:33:28
 */
@RestController
@RequestMapping("subHouse")
public class SubHouseController {
    /**
     * 服务对象
     */
    @Resource
    private SubHouseService subHouseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SubHouse selectOne(Integer id) {
        return this.subHouseService.queryById(id);
    }

    /**
     * 测试
     */
    @RequestMapping("subTest")
    public HttpResult subTest() throws ParseException {
        //sT=2020-03-27T16:00:00.000Z&eT=2020-03-28T16:00:00.000Z
        List<SubHouse> subHouses = subHouseService.queryAllByLimit(0, 99);
        for (SubHouse v : subHouses) {
//            System.err.println(v.getStartTime() + "===" + v.getEndTime());
//            System.out.println();
        }
        SubHouse subHouse = new SubHouse();

        subHouse.setStartTime(subHouses.get(0).getStartTime());
        System.out.println(subHouses.get(0).getStartTime()+"start====>");

        subHouse.setEndTime(subHouses.get(2).getEndTime());
        System.out.println(subHouses.get(2).getEndTime() +"end====>");

//        List<SubHouse> subRes = subHouseService.queryAllByTime(subHouse);

//        for (SubHouse v:subRes) {
//            System.out.println(v.getId());
//        }
        /////////
        String a1 = "2020-03-27T16:00:00.000Z";
        String a2 = "2020-03-28T16:00:00.000Z";
//        a1 = a1.replace("T"," ").replace("Z","");
//        a2 = a1.replace("T"," ").replace("Z","");
        System.out.println(DateUtils.strToDateLong(a1));
        System.out.println();
        System.out.println(DateUtils.strToDateLong(a2));

        SubHouse sh = new SubHouse();
        sh.setStartTime(DateUtils.strToDateLong(a1));
        sh.setEndTime(DateUtils.strToDateLong(a2));
//        List<SubHouse> subHouses1 = subHouseService.queryAllByTime(sh);

//        for (SubHouse v:subHouses1) {
//            System.out.println(v.getId()+" =v=");
//        }

        return HttpResult.success();
    }


}