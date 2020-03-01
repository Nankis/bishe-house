package com.cj.bishe.controller;

import com.cj.bishe.entity.User;
import com.cj.bishe.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-03-01 17:53:30
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getUserById")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

}