/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 11:03
 * @Since:
 */
package com.zja.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;

public class OSSMainTests {

    public static void main(String[] args) {
        OSS ossClient = ossClient();
        try {
            ossClient.putObject("bucket-demo-1", "123", new File("D:\\Temp\\picture\\3840x2160.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }

    /**
     * 初始化客户端
     */
    public static OSS ossClient() {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        //示例：oss-cn-shanghai.aliyuncs.com
        String endpoint = "";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        //示例：LTAI8tMMHfCKRrxYPvDjv5RN
        String accessKeyId = "";
        //示例：62aqMKbiwvrv8GG72ia6oqUTJkt4Pw
        String accessKeySecret = "";
        // 创建OSSClient实例。
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
