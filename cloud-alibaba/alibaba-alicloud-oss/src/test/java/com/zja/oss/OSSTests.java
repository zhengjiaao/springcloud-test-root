/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 11:16
 * @Since:
 */
package com.zja.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;
import java.time.Instant;
import java.util.Date;

@SpringBootTest
public class OSSTests {

    /**
     * {@link OSSConfig#ossClient()}
     *
     */
    @Autowired
    private OSS ossClient;

    /**
     * 桶名
     *
     * 桶管理：https://oss.console.aliyun.com/bucket
     */
    String bucketName = "bucket-demo-1";

    //key 值 重复会被覆盖
        String key = "123";
//    String key = "123.jpg";

    /**
     * 创建桶
     */
    @Test
    public void createBucket_test() {
        Bucket bucket = ossClient.createBucket(bucketName);
        System.out.println(bucket.getName());
    }

    /**
     * 上传对象(文件等)
     */
    @Test
    public void putObject_test() {
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, key, new File("D:\\Temp\\picture\\3840x2160.jpg"));
        System.out.println(putObjectResult);
    }

    /**
     * 获取对象
     */
    @Test
    public void getObject_test() {
        OSSObject object = ossClient.getObject(bucketName, key);
        System.out.println(object.getKey());
        System.out.println(object.getObjectMetadata());
//        InputStream stream = object.getObjectContent();
    }

    /**
     * 获取对象链接 (一般下载或预览)
     */
    @Test
    public void generatePresignedUrl_test() {
        //设置Url的过期时间 60秒
        URL url = ossClient.generatePresignedUrl(bucketName, key, Date.from(Instant.now().plusSeconds(60)));
        System.out.println(url);
    }

    /**
     * 删除对象
     */
    @Test
    public void deleteObject_test() {
        ossClient.deleteObject(bucketName, key);
    }

    /**
     * 删除桶 , 若 桶不为空，删除汇报异常  BucketNotEmpty
     */
    @Test
    public void deleteBucket_test() {
        ossClient.deleteBucket(bucketName);
    }

}
