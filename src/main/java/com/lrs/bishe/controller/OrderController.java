package com.lrs.bishe.controller;

import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.HttpResult;
import com.lrs.bishe.entity.HouseRent;
import com.lrs.bishe.entity.Order;
import com.lrs.bishe.entity.SubHouse;
import com.lrs.bishe.service.HouseRentService;
import com.lrs.bishe.service.OrderService;
import com.lrs.bishe.service.SubHouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2020-03-20 16:55:32
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    @Resource
    private HouseRentService houseRentService;

    @Resource
    private SubHouseService subHouseService;

    /**
     * 添加订单
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Order selectOne(Integer id) {
        return this.orderService.queryById(id);
    }

    @RequestMapping("addOrder")
    public HttpResult addOrder(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String[] split = result.split("&");
        System.out.println(result);
        String houseId = "";
        String userId = "";
        String sumPrice = "";
        String st = "";
        String et = "";
        for (String v : split) {
            if (v.contains("house_id=")) {
                houseId = v.replace("house_id=", "");
            }
            if (v.contains("user_id=")) {
                userId = v.replace("user_id=", "");
            }
            if (v.contains("sumPrice=")) {
                sumPrice = v.replace("sumPrice=", "").replace("￥", "");
            }
            if (v.contains("subTime[0]=")) {
                st = v.replace("subTime[0]=", "");
            }
            if (v.contains("subTime[1]=")) {
                et = v.replace("subTime[1]=", "");
            }
        }

        if ("".equals(houseId) || "".equals(userId)) {
            return HttpResult.fail("555", "请登录！");
        }

        if ("".equals(st) || "".equals(et)) {
            return HttpResult.fail("556", "日期不能为空！");
        }

        st = DateUtils.strToDateLongZs(st);
        et = DateUtils.strToDateLongZs(et);
        String subFlag = subHouseService.queryHaveSubByTimeAndHouse(st, et, Integer.parseInt(houseId));
        if (subFlag != null) {
            return HttpResult.fail("101", "该时间段已有人预约! 其预约时间为:" + subFlag);
        } else if (DateUtils.strToDateLong(st).getTime() < System.currentTimeMillis()) {
            return HttpResult.fail("111", "不能预约过去的时间!");
        } else {
            SubHouse subHouse = new SubHouse();
            subHouse.setStartTime(DateUtils.strToDateLong(st));
            subHouse.setEndTime(DateUtils.strToDateLong(et));
            subHouse.setUserid(Integer.parseInt(userId));
            subHouse.setHouseid(Integer.parseInt(houseId));
            subHouseService.insert(subHouse);
        }

        HouseRent houseRent = houseRentService.queryById(Integer.parseInt(houseId));

        Order order = new Order();
        order.setHouseid(Integer.parseInt(houseId));
        order.setCustomer(userId);
        order.setPrice(Double.parseDouble(sumPrice));
        order.setMaster(houseRent.getAdminId().toString());
        orderService.insert(order);
        return HttpResult.success(true);
    }

}