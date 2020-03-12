package com.cj.bishe.service.impl;

import com.cj.bishe.entity.Collect;
import com.cj.bishe.entity.House;
import com.cj.bishe.entity.User;
import com.cj.bishe.entity.vo.HouseCollectVO;
import com.cj.bishe.service.CollectService;
import com.cj.bishe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Resource
    CollectService collectService;

    @Resource
    UserService userService;


    @Test
    public void getUserCollect() {
//        List<House> userCollect = userService.getUserCollect(1);
//        for (House v : userCollect) {
//            System.err.println(v.getHouseName());
//        }
        System.err.println(userService.collectHouse(2, 2));
    }

}