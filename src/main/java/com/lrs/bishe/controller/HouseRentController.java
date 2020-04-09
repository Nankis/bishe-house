package com.lrs.bishe.controller;

import com.lrs.bishe.common.DateUtils;
import com.lrs.bishe.common.HttpResult;
import com.lrs.bishe.entity.HousePic;
import com.lrs.bishe.entity.HouseRent;
import com.lrs.bishe.entity.SubHouse;
import com.lrs.bishe.service.HousePicService;
import com.lrs.bishe.service.HouseRentService;
import com.lrs.bishe.service.SubHouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * (HouseRent)表控制层
 *
 * @author makejava
 * @since 2020-03-16 15:19:56
 */
@RestController
@RequestMapping("houseRent")
public class HouseRentController {
    /**
     * 服务对象
     */
    @Resource
    private HouseRentService houseRentService;

    @Resource
    private HousePicService housePicService;

    @Resource
    private SubHouseService subHouseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getById")
    public HouseRent selectOne(Integer id) {
        return this.houseRentService.queryById(id);
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    @RequestMapping("getAllHouseRent")
    public HttpResult getAllHouseRent() {
        List<HouseRent> houseRents = this.houseRentService.queryAllByLimit(0, 999);
        Map<String, Object> datas = new HashMap<>();
        datas.put("TotalCount", houseRents.size());//数据条数
        //封装图片
        houseRentService.getPic(houseRents);
        datas.put("_Items", houseRents);
        return HttpResult.successForPage(datas, houseRents.size());
    }

    /**
     * 获取全部数据  包含所有条件
     *
     * @return
     */
    @RequestMapping("getAllConditionHouse")
    public HttpResult getAllConditionHouse() {
        List<HouseRent> houseRents = this.houseRentService.queryAllByLimits(0, 999);
        Map<String, Object> datas = new HashMap<>();
        datas.put("TotalCount", houseRents.size());//数据条数
        //封装图片
        houseRentService.getPic(houseRents);
        datas.put("_Items", houseRents);
        return HttpResult.successForPage(datas, houseRents.size());
    }

    /**
     * 默认请求参数
     *
     * @param reqData
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("getAllHouseRentByConditions")
    public HttpResult getAllHouseRentByConditions(@RequestBody String reqData) throws UnsupportedEncodingException {

        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        System.err.println(result);
        String[] res = result.split("&");
        String title = null;

        //预约时间
        String sT = null;
        String eT = null;

        //默认查询最低租金
        double rent1 = 0.0;
        //默认查询最高租金
        double rent2 = 9999999.0;
        //默认查询最低ya yi fu yi
        int month1 = 1;
        //ya yi fu san
        int month2 = 3;
        List<HouseRent> houseShapeList = null;
        for (String v : res) {
            //根据房屋标题查询
            if (v.contains("house_title=")) {
                title = v.replace("house_title=", "");
                title = "%" + title + "%";
            }
            //处理查询的租金范围
            if (v.contains("rent_str=")) {
                String rentStr = v.replace("rent_str=", "");
                if (rentStr.trim().length() > 0) {
                    String[] split = rentStr.split(",");
                    rent1 = Double.parseDouble(split[0]);
                    rent2 = Double.parseDouble(split[1]);
                }
            }
            //处理折扣支持方式 包月..季 年
            if (v.contains("months_str=")) {
                String monthStr = v.replace("months_str=", "");
                //必须要1，2 || 2，3 这种(逗号算一个值)
                if (monthStr.length() > 0 && monthStr.length() <= 3) {
                    String[] split = monthStr.split(",");
                    month1 = Integer.parseInt(split[0]);
                    if (split.length == 1) {
                        //只有单选时
                        month2 = month1;
                    }
                    if (split.length > 1) {
                        month2 = Integer.parseInt(split[1]);
                    }
                }
            }
            //处理民宿或学校 shape_str
            if (v.contains("shape_str=")) {
                String shapeStr = v.replace("shape_str=", "");
                //若是3个条件都选择，则相当于默认查询全部
                if (shapeStr.length() > 0 && shapeStr.length() <= 5) {
                    String[] split = shapeStr.split(",");
                    String condition1 = split[0];
                    String condition2 = "";
                    if (split.length >= 2) {
                        condition2 = split[1];
                    }
                    //分别通过条件1，2去查询出两个结果集，然后对两个结果集取并集
                    HouseRent houseRent = new HouseRent();
                    houseRent.setHouseShape("%" + condition1 + "%");
                    houseShapeList = houseRentService.queryAllByConditions(houseRent);

                    if (!"".equals(condition2)) {
                        houseRent.setHouseShape("%" + condition2 + "%");
                        List<HouseRent> listCondition2 = houseRentService.queryAllByConditions(houseRent);
                        //取并集操作1.去重，2.添加非重复元素
                        houseShapeList.removeAll(listCondition2);
                        houseShapeList.addAll(listCondition2);
                    }

                }
            }

            if (v.contains("s_T=")) {
                sT = DateUtils.strToDateLongZs(v.replace("s_T=",""));
            }
            if (v.contains("e_T=")) {
                eT = DateUtils.strToDateLongZs(v.replace("e_T=",""));
            }
        }
        HouseRent houseRent = new HouseRent();
        houseRent.setHouseTitle(title);
        List<HouseRent> houseRentList = houseRentService.queryAllByConditions(houseRent);
        //说明查询了居室类型，需要再取并集
        if (houseShapeList != null) {
            houseRentList.retainAll(houseShapeList);
        }

        //再查询租金和押金方式（有默认方式，所以必须查询）
        List<HouseRent> rentAndMonthRes = houseRentService.queryAllByRentOrMonths(rent1, rent2, month1, month2);
        houseRentList.retainAll(rentAndMonthRes);

        //最后处理查询预约时间段内是否有预约过
        List<HouseRent> subHouseList = new ArrayList<>();
        if (sT != null && eT != null) {
            List<SubHouse> subList = subHouseService.queryAllByTime(sT,eT);
            if (subList.size() > 0) {
                for (SubHouse v : subList) {
                    subHouseList.add(houseRentService.queryById(v.getHouseid()));
                }
            }
        }
        if (subHouseList.size() > 0) {
            //删除该时间段已被预约的自习室
            houseRentList.removeAll(subHouseList);
        }

        Map<String, Object> datas = new HashMap<>();
        datas.put("TotalCount", houseRentList.size());
        //封装图片
        houseRentService.getPic(houseRentList);
        datas.put("_Items", houseRentList);
        return HttpResult.successForPage(datas, houseRentList.size());
    }

    /**
     * 根据房屋id获取房屋详情
     *
     * @param houseId
     * @return
     */
    @RequestMapping("getHouseDetailById")
    public HttpResult getHouseDetailById(@RequestBody String houseId) {
        //参数格式转换
        int id = 0;
        id = Integer.parseInt(houseId.replace("house_id=", ""));
//        System.out.println("getHouseDetailById"+id);
        HashMap<String, Object> res = houseRentService.getDetailHouse(id);

        return HttpResult.successForPage(res, res.size());

    }

    /**
     * 一部分源于用户收藏
     *
     * @return
     */
    @RequestMapping("queryHouseByCollect")
    public HttpResult queryHouseByCollect() {
        //参数格式转换
        List<Object> Data = new ArrayList<>();
        Random random = new Random();
        //随机推荐1~5条房源
        int i = random.nextInt(5);
        List<HouseRent> houseRents = houseRentService.queryAllByLimit(0, i + 1);
        Map<String, Object> obj = null;
        for (HouseRent v : houseRents) {
            obj = new HashMap<>();
            obj.put("House_id", v.getHouseId());
            obj.put("House_collectAmount", 1);//收藏次数，默认1
            obj.put("House_coverPic", v.getHouseCoverpic());
            HousePic housePic = new HousePic();
            housePic.setHouseId(v.getHouseId());
            List<HousePic> housePics = housePicService.queryAll(housePic);
            List<String> picUrl = new ArrayList<>();
            for (HousePic pic : housePics) {
                picUrl.add(pic.getPicUrl());
            }
            obj.put("House_picList", picUrl);
            Data.add(obj);
        }

        return HttpResult.successForPage(Data, Data.size());

    }

    /**
     * 发布
     * 请求参数
     *
     *
     * @param reqData
     * @return
     */
    @RequestMapping("pubHouse")
    public HttpResult pubHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        boolean b = houseRentService.pubHouse(result);
        if (!b) {
            return HttpResult.fail("555", "数据不能为空！");
        }
        return HttpResult.success(b);
    }

    /**
     *
     * 请求参数：House_id=9&userId=2
     *
     * @param reqData
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("rentHouse")
    public HttpResult rentHouse(@RequestBody String reqData) throws UnsupportedEncodingException {
        String result = java.net.URLDecoder.decode(reqData, StandardCharsets.UTF_8.name());
        String[] split = result.split("&");
        String houseId = "";
        String userId = "";
        for (String v : split) {
            if (v.contains("House_id=")) {
                houseId = v.replace("House_id=", "");
            }
            if (v.contains("userId=")) {
                userId = v.replace("userId=", "");
            }
        }

        if ("".equals(houseId) || "".equals(userId)) {
            return HttpResult.fail("555", "数据不能为空！");
        }



        return HttpResult.success(true);
    }


}