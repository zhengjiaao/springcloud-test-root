/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 10:35
 * @Since:
 */
package com.zja.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {

    /**
     * 站点
     * yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为 https://oss-cn-hangzhou.aliyuncs.com
     */
    @Value("${alibaba.oss.endpoint}")
    public String endpoint;
    /**
     * 访问密钥 ID
     * 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户
     */
    @Value("${alibaba.oss.access-key}")
    public String accessKeyId;
    /**
     * 访问密钥
     */
    @Value("${alibaba.oss.secret-key}")
    public String accessKeySecret;


    /**
     * 创建OSSClient实例
     */
    @Bean
    public OSS ossClient() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 关闭 OSSClient
//        ossClient.shutdown();
        return ossClient;
    }

}
