/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-01-05 13:36
 * @Since:
 */
package com.zja.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class SendSms {
    public static void main(String[] args) {
        String regionId = "cn-hangzhou";
        //填写，自己的访问密钥
        String accessKeyId = "";
        String accessSecret = "";

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessSecret);//自己账号的AccessKey信息
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//短信服务的服务接入地址
        request.setSysVersion("2017-05-25");//API的版本号
        request.setSysAction("SendSms");//API的名称
//        request.putQueryParameter("PhoneNumbers", "1503871****");//接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", "1583786***");//接收短信的手机号码
        request.putQueryParameter("SignName", "阿里云短信测试");//短信签名名称
        request.putQueryParameter("TemplateCode", "SMS_154950909");//短信模板ID
        request.putQueryParameter("TemplateParam", "{\"code\":\"1111\"}");//短信模板变量对应的实际值
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            //由于短信服务市收费的，需要付款，不多演示
            //{"RequestId":"635FA3BC-7CB3-5506-B632-146D73F89DC1","Message":"账户余额不足","Code":"isv.AMOUNT_NOT_ENOUGH"}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
