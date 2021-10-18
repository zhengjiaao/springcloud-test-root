/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-10-18 10:54
 * @Since:
 */
package com.zja;

import com.zja.nacos.MyAttributes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 */
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private MyAttributes myAttributes;

    @Test
    public void test(){
        System.out.println(myAttributes);
    }
}
