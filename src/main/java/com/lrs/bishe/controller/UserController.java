package com.lrs.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.HttpResult;
import com.lrs.bishe.entity.*;
import com.lrs.bishe.enums.ResultMsgEnum;
import com.lrs.bishe.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-11 17:53:30
 */
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    @Resource
    private HouseRentService houseRentService;

    @Resource
    private CollectService collectService;

    @Resource
    private VipService vipService;

    @Resource
    private SubHouseService subHouseService;

    @Resource
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getUserById")
    public HttpResult getUserById(Integer id) {
        //根据该用户名判断用户是否存在
        if (userService.queryById(id) == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_USER);
        }
        return HttpResult.success(userService.queryById(id));
    }

    /**
     * 用户注册
     * 必填参数 username，pwd，name，type
     *
     * @param reqData
     * @return
     */
    @RequestMapping("addUser")
    public HttpResult addUser(@RequestBody String reqData) throws UnsupportedEncodingException {
        logger.info("addUser reqData:{}", reqData);
        if (StringUtils.isBlank(reqData)) {
            return HttpResult.fail(ResultMsgEnum.ILLEGAL_ARGS);
        }
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result.replace("user=", ""));
        JSONObject jsonObject = JSONObject.parseObject(result.replace("user=", ""));
        String userStr = jsonObject.toString();
        //根据前端传的参数生成一个user
        User user = JSONObject.parseObject(userStr, User.class);

        //根据该用户名判断用户是否存在
        if (userService.queryByUserName(user.getUsername()) != null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_EXIST_USER);
        }
        return HttpResult.success(userService.insert(user));
    }

    @RequestMapping("login")
    public HttpResult login(@RequestBody String data) {
        String[] res = data.split("&");
        String username = res[0].replace("loginInfo=", "");
        String pwd = res[1].replace("user_pwd=", "");
        User user = userService.queryByUserName(username);
        if (user == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_USER);
        } else if (!pwd.equals(user.getPwd())) {
            return HttpResult.fail("999", "密码错误");
        }
        return HttpResult.success(user);
    }

    @RequestMapping("updateUser")
    public HttpResult updateUser(@RequestBody String reqData) {
        logger.info("updateUser reqData:{}", reqData);
        if (StringUtils.isBlank(reqData)) {
            return HttpResult.fail(ResultMsgEnum.ILLEGAL_ARGS);
        }
        String[] res = reqData.split("&");
        String username = res[0].replace("username=", "");
        String pwd = res[1].replace("password=", "");
        User user = userService.queryByUserName(username);

        //根据前端传的参数生成一个user
//        User user = JSONObject.parseObject(reqData, User.class);

        //根据该用户名判断用户是否存在
        if (userService.queryByUserName(user.getUsername()) == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_USER);
        }
        user.setPwd(pwd);
        return HttpResult.success(userService.updateUserByUserName(user));
    }

    /**
     * http://localhost:8080/user/collect?username="aaa"
     * 通过username和house的参数来更新用户收藏
     *
     * @param request
     * @return
     */
    @RequestMapping("getCollectHouseByUserName")
    public HttpResult getCollectHouseByUserName(HttpServletRequest request) {
        String userName = request.getParameter("username");
        User user = userService.queryByUserName(userName);
        if (user == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_USER);
        }
        List<HouseRent> userCollect = userService.getUserCollect(user.getId());
        return HttpResult.success(userCollect);
    }

    @RequestMapping("collectHouse")
    public HttpResult collectHouse(int userId, int houseId) {
        User user = userService.queryById(userId);
        if (user == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_USER);
        }
        HouseRent house = houseRentService.queryById(houseId);
        if (house == null) {
            return HttpResult.fail(ResultMsgEnum.NOTICE_NOT_EXIST_HOUSE);
        }
        boolean res = userService.collectHouse(userId, houseId);
        return HttpResult.success(res);
    }

    /**
     * 查看该用户
     *
     * @param reqData
     * @return
     */
    @RequestMapping("getRentHouse")
    public HttpResult getRentHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String userId = result.replace("user_id=", "");
        HouseRent houseRent = new HouseRent();
        houseRent.setUserId(Integer.parseInt(userId));
        SubHouse subHouse = new SubHouse();
        subHouse.setUserid(Integer.parseInt(userId));
        //该用户预约过的所有记录
        List<SubHouse> subHouses = subHouseService.queryAll(subHouse);

        List<HouseRent> res = new ArrayList<>();
        for (SubHouse v : subHouses) {
            HouseRent hr = houseRentService.queryById(v.getHouseid());
            hr.setSubTime(DateUtils.dateToStringFormat(v.getStartTime()) + "~" + DateUtils.dateToStringFormat(v.getEndTime()));
            //设置优惠的价格， 需要保证有订单
            Order order = orderService.queryByHouseIdAndUserId(v.getHouseid(), Integer.parseInt(userId)).get(0);
            //设置优惠后的价格
            hr.setHouseRent(order.getPrice());
            //返回前端该预约记录的id，方便取消预约操作
            hr.setSubId(v.getId());
            res.add(hr);
        }
        return HttpResult.successForPage(res, res.size());
    }


    /**
     * @param reqData
     * @return
     */
    @RequestMapping("removeRentHouse")
    public HttpResult removeRentHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
//        String houseId_str = result.replace("house_id=", "");
//        String[] split = houseId_str.split(",");
        String subIdStr = "";
        String[] split = result.split("&");
        for (String v : split) {
            if (v.contains("subId=")) {
                subIdStr = v.replace("subId=", "");
            }
        }
        String[] splitSubId = subIdStr.split(",");
        for (String v : splitSubId) {
            subHouseService.deleteById(Integer.parseInt(v));
        }
//        HouseRent houseRent = new HouseRent();
//        for (String v : split) {
//            houseRent.setHouseId(Integer.parseInt(v));
//            houseRent.setUserId(-1);
//            houseRent.setHouseIsrented("N");
//            houseRentService.update(houseRent);
//        }
        return HttpResult.success();
    }

    /**
     * 用户详情页面添加或取消收藏
     *
     * @param reqData
     * @return
     */
    @RequestMapping("addOwnCollect")
    public HttpResult addOwnCollect(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        String houseIdStr = "";

        for (String v : split) {
            if (v.contains("user_id=")) {
                userIdStr = v.replace("user_id=", "");
            }
            if (v.contains("house_id")) {
                houseIdStr = v.replace("house_id=", "");
            }
        }
        int userId = Integer.parseInt(userIdStr);
        int houseId = Integer.parseInt(houseIdStr);

        Collect collectIsExist = collectService.queryByUserIdAndHouseId(userId, houseId);
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setHouseId(houseId);

        if (collectIsExist == null) {
            //收藏
            collectService.insert(collect);
        } else {
            //取消收藏
            collectService.deleteById(collectIsExist.getId());
        }
        return HttpResult.success(true);
    }

    /**
     * 查询收藏
     *
     * @param reqData
     * @return
     */
    @RequestMapping("queryOwnCollect")
    public HttpResult queryOwnCollect(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        String houseIdStr = "";

        for (String v : split) {
            if (v.contains("user_id=")) {
                userIdStr = v.replace("user_id=", "");
            }
            if (v.contains("house_id")) {
                houseIdStr = v.replace("house_id=", "");
            }
        }
        int userId = Integer.parseInt(userIdStr);
        int houseId = Integer.parseInt(houseIdStr);
        Collect collect = collectService.queryByUserIdAndHouseId(userId, houseId);
        boolean res = collect != null;
        return HttpResult.success(res);
    }

    /**
     * 查询个人中心收藏列表
     *
     * @param reqData
     * @return
     */
    @RequestMapping("queryOwnCollectList")
    public HttpResult queryOwnCollectList(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";

        for (String v : split) {
            if (v.contains("user_id=")) {
                userIdStr = v.replace("user_id=", "");
            }
        }
        int userId = Integer.parseInt(userIdStr);
        Collect collect = new Collect();
        collect.setUserId(userId);
        List<Collect> collects = collectService.queryAll(collect);
        List<HouseRent> res = new ArrayList<>();
        for (Collect v : collects) {
            //收藏列表 两种互斥情况
            HouseRent houseRent = houseRentService.queryById(v.getHouseId());
            if (houseRent == null) {
                houseRent = houseRentService.queryByIdISY(v.getHouseId());
            }
            res.add(houseRent);
        }
        return HttpResult.successForPage(res, res.size());
    }

    /**
     * 移除收藏列表
     *
     * @param reqData
     * @return
     */
    @RequestMapping("deleteOwnCollectBatchList")
    public HttpResult deleteOwnCollectBatchList(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        String houseIdStr = "";
        String[] splitHouseId = null;
        for (String v : split) {
            if (v.contains("user_id=")) {
                userIdStr = v.replace("user_id=", "");
            }
            if (v.contains("house_id=")) {
                houseIdStr = v.replace("house_id=", "");
                splitHouseId = houseIdStr.split(",");
            }
        }
        int userId = Integer.parseInt(userIdStr);
        Collect collect = new Collect();
        collect.setUserId(userId);

        if (splitHouseId != null) {
            for (String v : splitHouseId) {
                collect.setHouseId(Integer.parseInt(v));
                collectService.deleteByUserIdAndHouseId(collect);
            }
        }

        return HttpResult.success(true);
    }


    /**
     * 移除无效自习室
     *
     * @param reqData
     * @return
     */
    @RequestMapping("deleteRentHouse")
    public HttpResult deleteRentHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        String houseIdStr = "";
        String[] splitHouseId = null;
        for (String v : split) {
            if (v.contains("user_id=")) {
                userIdStr = v.replace("user_id=", "");
            }
            if (v.contains("house_id=")) {
                houseIdStr = v.replace("house_id=", "");
                splitHouseId = houseIdStr.split(",");
            }
        }

        if (splitHouseId != null) {
            for (String v : splitHouseId) {
                houseRentService.deleteById(Integer.parseInt(v));
            }
        }

        return HttpResult.success(true);
    }

    /**
     * addVip充值会员
     *
     * @param reqData
     * @return
     */
    @RequestMapping("addVip")
    public HttpResult addVip(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        String vipType = "";
        for (String v : split) {
            if (v.contains("userId=")) {
                userIdStr = v.replace("userId=", "");
            }
            if (v.contains("vipType=")) {
                vipType = v.replace("vipType=", "");
            }
        }

        if ("".equals(userIdStr)) {
            return HttpResult.fail(ResultMsgEnum.NOT_PERMISSION);
        }

        Vip vip = new Vip();
        vip.setUserid(Integer.parseInt(userIdStr));
        vip.setViptype(vipType);

        Vip vipExist = vipService.queryByUserId(Integer.parseInt(userIdStr));
        if (vipExist == null) {
            vipService.insert(vip);
        } else {
            vipExist.setViptype(vipType);
            vipService.update(vipExist);
        }

        return HttpResult.success(true);
    }

    /**
     * addVip充值会员
     *
     * @param reqData
     * @return
     */
    @RequestMapping("getVipType")
    public HttpResult getVipType(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String[] split = result.split("&");
        String userIdStr = "";
        for (String v : split) {
            if (v.contains("userId=")) {
                userIdStr = v.replace("userId=", "");
            }
        }

        if ("".equals(userIdStr)) {
            return HttpResult.fail(ResultMsgEnum.NOT_PERMISSION);
        }

        Vip vip = new Vip();
        vip.setUserid(Integer.parseInt(userIdStr));
        Vip vipExist = vipService.queryByUserId(Integer.parseInt(userIdStr));
        String res = "-1";
        if (vipExist != null) {
            res = vipExist.getViptype();
        }
        return HttpResult.success(res);
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("getUsers")
    public HttpResult getUsers() {
        User user = new User();
        List<User> users = userService.queryAll(user);
        //超管不给予显示
        users.removeIf(u -> u.getId() == 1);
        return HttpResult.successForPage(users, users.size());
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("changeAuth")
    public HttpResult changeAuth(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String replace = result.replace("userId=", "");
        String[] split = replace.split(",");
        for (String v : split) {
            User user = userService.queryById(Integer.parseInt(v));
            if ("1".equals(user.getType())) {
                user.setType("2");
            } else {
                user.setType("1");
            }
            userService.update(user);
        }
        return HttpResult.success();
    }

    /**
     * 获取用户类别
     *
     * @return
     */
    @RequestMapping("queryUserType")
    public HttpResult queryUserType(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String userId = result.replace("user_id=", "");
        User user = userService.queryById(Integer.parseInt(userId));
        return HttpResult.success(user.getType());
    }

}