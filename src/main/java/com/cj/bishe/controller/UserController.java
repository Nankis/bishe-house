package com.cj.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.cj.bishe.common.HttpResult;
import com.cj.bishe.entity.Collect;
import com.cj.bishe.entity.HouseRent;
import com.cj.bishe.entity.User;
import com.cj.bishe.enums.ResultMsgEnum;
import com.cj.bishe.service.CollectService;
import com.cj.bishe.service.HouseRentService;
import com.cj.bishe.service.UserService;
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
     * 通过username和house的参数来更新用户收藏的房子
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
     * 查看该用户已租的房源
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
        List<HouseRent> houseRents = houseRentService.queryAll(houseRent);
        return HttpResult.successForPage(houseRents, houseRents.size());
    }


    /**
     * 用户主动退租
     *
     * @param reqData
     * @return
     */
    @RequestMapping("removeRentHouse")
    public HttpResult removeRentHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.out.println(result);
        String houseId_str = result.replace("house_id=", "");
        String[] split = houseId_str.split(",");
        HouseRent houseRent = new HouseRent();
        for (String v : split) {
            houseRent.setHouseId(Integer.parseInt(v));
            houseRent.setUserId(-1);
            houseRent.setHouseIsrented("N");
            houseRentService.update(houseRent);
        }
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
     * 移除无效房源
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
}