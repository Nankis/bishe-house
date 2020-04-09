package com.lrs.bishe.controller;

import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.HttpResult;
import com.lrs.bishe.entity.Notice;
import com.lrs.bishe.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * (Notice)表控制层
 *
 * @author makejava
 * @since 2020-03-27 00:19:07
 */
@RestController
@RequestMapping("notice")
public class NoticeController {
    /**
     * 服务对象
     */
    @Resource
    private NoticeService noticeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Notice selectOne(Integer id) {
        return this.noticeService.queryById(id);
    }

    /**
     * 获取最新公告
     *
     * @return 单条数据
     */
    @RequestMapping("getLastNotice")
    public HttpResult getLastNotice() {
        List<Notice> notices = noticeService.queryLastNotice();
        Notice notice = null;
        if (notices.size() >= 1) {
            notice = notices.get(0);
            notice.setPubTime(DateUtils.dateToStringFormat(notice.getCreatedTime()));
        }
        return HttpResult.success(notice);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @RequestMapping("pubNotice")
    public HttpResult pubNotice(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String[] split = result.split("&");
        Notice notice = new Notice();
        String title = null;
        String userId = null;
        String content = null;
        for (String v : split) {
            if (v.contains("title=")) {
                title = v.replace("title=", "");
            }
            if (v.contains("userId=")) {
                userId = v.replace("userId=", "");
            }
            if (v.contains("contenxt=")) {
                content = v.replace("contenxt=", "");
            }
        }
        if ("".equals(title) || "".equals(content)) {
            return HttpResult.fail("909", "数据不能为空");
        }
        notice.setTitle(title);
        notice.setUserid(Integer.parseInt(userId));
        notice.setContenxt(content);
        noticeService.insert(notice);
        return HttpResult.success(true);
    }

}