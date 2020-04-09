package com.lrs.bishe.common;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 日期工具类
 *
 * @author shijing
 *
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /***
     * Date类型转换成XMLGregorianCalendar类型
     *
     * @param date
     * @return
     */
    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            logger.error("Date类型转换成XMLGregorianCalendar类型出错：" + e);
        }
        return gc;
    }

    /***
     * XMLGregorianCalendar类型转换成Date类型
     *
     * @param cal
     * @return
     * @throws Exception
     */
    public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    /**
     * @param str    日期字符串
     * @param format 转换格式
     * @return Date
     */
    public static Date stringToDate(String str, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("String类型 转 Date类型出错：" + e);
        }
        return date;
    }

    /**
     * Date 转 String
     * auther: shijing
     *
     * @param date   日期
     * @param format 转换格式
     * @return
     */
    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            if (date != null) {
                strDate = dateFormat.format(date);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("Date类型 转 String类型出错：" + e);
        }
        return strDate;

    }

    /**
     * Date转标准格式日期
     *
     * @param date
     * @return
     */
    public static String dateToStringFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置时区，跳过此步骤会默认设置为"GMT+08:00" 得到的结果会多出来8个小时
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return sdf.format(date);
    }


    /**
     *     将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *    
     *     @param strDate
     *     @return
     *    
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     *     将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *     转换中国时区的
     *     @param strDate
     *     @return
     *    
     */
    public static Date strToDateLongZ(String strDate) {
        strDate = strDate.replace("Z", " UTC");
        //转换时区格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        //将Z时间格式转换成Date类型格式或换成毫秒 long ldate = format1 .parse(dateTime ).getTime()
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("解析后的时间为 :"+format2.format(date));
        return date;
    }

    /**
     * 直接获取String类型
     * @param strDate
     * @return
     */
    public static String strToDateLongZs(String strDate) {
        strDate = strDate.replace("Z", " UTC");
        //转换时区格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        //将Z时间格式转换成Date类型格式或换成毫秒 long ldate = format1 .parse(dateTime ).getTime()
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String res = format2.format(date);
        System.out.println("解析后的时间为 :"+res);
        return res;
    }

}