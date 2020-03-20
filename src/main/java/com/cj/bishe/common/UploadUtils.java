package com.cj.bishe.common;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.IOException;

public class UploadUtils {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    static String ACCESS_KEY = "P958e392YaN5rZdGoYCquqhY72ssevEjJfDJtey-";
    static String SECRET_KEY = "d8CckPP8XCa3pFqfc-UrRKFvXSxgaMFivR57Ux2g";
    //要上传的空间
    static String bucketname = "cjbsimg";
    //上传到七牛后保存的文件名
    static String key = "test.png";
    //上传文件的路径
    static String FilePath = "D:\\TEST\\img\\404.jpg";
    static String preUrl = "http://q74deg0wd.bkt.clouddn.com/";
    //密钥配置
    static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    static Zone z = Zone.autoZone();
    static Configuration c = new Configuration(z);

    //创建上传对象
    static UploadManager uploadManager = new UploadManager(c);

//    public static void main(String args[]) throws IOException {
//        new UploadUtils().upload();
//    }

    /**
     * 生成上传token
     *
     * @param bucket  空间名
     * @param key     key，可为 null
     * @param expires 有效时长，单位秒
     * @param policy  上传策略的其它参数，如 new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")。
     *                scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
     * @return 生成的上传token
     */
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken() {
        long   expires = 3160000L;
        StringMap policy = new StringMap();
        return auth.uploadToken(bucketname,null,expires,policy);
    }

    public static String upload() throws IOException {
        try {
            //调用put方法上传
            System.err.println(getUpToken());
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            System.err.println(preUrl + key);

        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return preUrl + key;
    }

    public static void main(String[] args) throws IOException {
        UploadUtils.upload();
    }
}
