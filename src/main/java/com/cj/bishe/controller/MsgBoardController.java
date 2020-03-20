package com.cj.bishe.controller;

import com.cj.bishe.common.DateUtils;
import com.cj.bishe.common.HttpResult;
import com.cj.bishe.entity.MsgBoard;
import com.cj.bishe.entity.User;
import com.cj.bishe.enums.ResultMsgEnum;
import com.cj.bishe.service.MsgBoardService;
import com.cj.bishe.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (MsgBoard)表控制层
 *
 * @author makejava
 * @since 2020-03-20 02:10:41
 */
@RestController
@RequestMapping("message")
public class MsgBoardController {
    /**
     * 服务对象
     */
    @Resource
    private MsgBoardService msgBoardService;

    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MsgBoard selectOne(Integer id) {
        return this.msgBoardService.queryById(id);
    }

    /**
     * 添加留言
     *
     * @param reqData
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("addMessage")
    public HttpResult addMessage(@RequestBody String reqData) throws UnsupportedEncodingException, ParseException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String[] split = result.split("&");
        String houseId = "";
        String userId = "";
        String msg = "";
        for (String v : split) {
            if (v.contains("house_id=")) {
                houseId = v.replace("house_id=", "");
            }
            if (v.contains("user_id=")) {
                userId = v.replace("user_id=", "");
            }
            if (v.contains("messages")) {
                msg = v.replace("messages=", "");
            }
        }
        if ("".equals(userId)) {
            return HttpResult.fail(ResultMsgEnum.NOT_LOGIN);
        }
        if ("".equals(msg)) {
            return HttpResult.fail("121","留言不能为空！");
        }

        MsgBoard msgBoard = new MsgBoard();
        msgBoard.setUser(userId);
        msgBoard.setHouseid(Integer.parseInt(houseId));
        msgBoard.setMsg(msg);
        msgBoardService.insert(msgBoard);
        return HttpResult.success(true);
    }

    /**
     * 获取留言
     *
     * @param reqData
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("houseMessage")
    public HttpResult houseMessage(@RequestBody String reqData) throws UnsupportedEncodingException, ParseException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String[] split = result.split("&");
        String houseId = "";
        for (String v : split) {
            if (v.contains("house_id=")) {
                houseId = v.replace("house_id=", "");
            }
        }
        MsgBoard msgBoard = new MsgBoard();
        msgBoard.setHouseid(Integer.parseInt(houseId));
        List<MsgBoard> msgBoards = msgBoardService.queryAll(msgBoard);
        User user;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (MsgBoard v : msgBoards) {
            user = userService.queryById(Integer.parseInt(v.getUser()));
            v.setName(user.getName());
            v.setCreateTime(sdf.format(v.getCreatedTime()));
        }
        return HttpResult.success(msgBoards);
    }

}