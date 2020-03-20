package com.cj.bishe.controller;

import com.cj.bishe.common.HttpResult;
import com.cj.bishe.entity.HouseRent;
import com.cj.bishe.entity.Order;
import com.cj.bishe.service.HouseRentService;
import com.cj.bishe.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

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
        for (String v : split) {
            if (v.contains("house_id=")) {
                houseId = v.replace("house_id=", "");
            }
            if (v.contains("user_id=")) {
                userId = v.replace("user_id=", "");
            }
            if (v.contains("sumPrice=")) {
                sumPrice = v.replace("sumPrice=", "");
            }
        }

        if ("".equals(houseId) || "".equals(userId)) {
            return HttpResult.fail("555", "请登录！");
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