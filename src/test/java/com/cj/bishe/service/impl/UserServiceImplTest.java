package com.cj.bishe.service.impl;

import com.cj.App;
import com.cj.bishe.service.CollectService;
import com.cj.bishe.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
//@ContextConfiguration(locations={"classpath:**/application.properties"})
@PropertySource(value = { "classpath:application.properties" })//加载配置文件
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
//        UploadUtils.upload();
        System.err.println(userService.collectHouse(2, 2));
    }

}