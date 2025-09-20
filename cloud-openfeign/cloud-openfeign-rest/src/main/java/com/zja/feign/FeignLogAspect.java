package com.zja.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: zhengja
 * @Date: 2025-09-11 16:43
 */
@Slf4j
@Aspect
@Component
public class FeignLogAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 针对特定类的方法
     */
    @Pointcut(value = "execution (* com.zja.feign..*Feign.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取方法签名
        String methodName = proceedingJoinPoint.getSignature().getName();

        // 执行目标方法并记录
        return proceedAndLog(proceedingJoinPoint, methodName);
    }

    /**
     * 执行目标方法并记录（耗时，异常等）
     */
    private Object proceedAndLog(ProceedingJoinPoint proceedingJoinPoint, String methodName) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 获取实际接口类名（声明类型）
        String className = ((org.aspectj.lang.reflect.MethodSignature) proceedingJoinPoint.getSignature())
                .getDeclaringTypeName();

        // 获取方法参数信息
        String methodSignature = getMethodSignature(proceedingJoinPoint);
        String argsJson = convertArgsToJson(proceedingJoinPoint.getArgs());

        // 记录请求开始日志
        log.info("[FEIGN] >>>>> 调用开始: {}.{}{} \n[参数]: {}",
                className, methodName, methodSignature, argsJson);

        try {
            Object result = proceedingJoinPoint.proceed();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // 记录成功响应日志
            log.info("[FEIGN] <<<<< 调用成功: {}.{}{} 耗时: {} ms \n[结果]: {}",
                    className, methodName, methodSignature, duration, convertResultToJson(result));

            return result;
        } catch (FeignException fe) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // 记录Feign异常日志
            log.error("[FEIGN] <<<<< 调用失败: {}.{}{} 状态码: {} 耗时: {} ms \n[请求参数]: {}\n[错误响应]: {}",
                    className, methodName, methodSignature, fe.status(), duration, argsJson, fe.contentUTF8());

            throw fe;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            // 记录其他异常日志
            log.error("[FEIGN] <<<<< 调用异常: {}.{}{} 耗时: {} ms \n[请求参数]: {}\n[异常信息]: {}",
                    className, methodName, methodSignature, duration, argsJson, e.getMessage(), e);

            throw e;
        }
    }

    /**
     * 获取方法签名信息，包括参数类型和参数名
     */
    private String getMethodSignature(ProceedingJoinPoint joinPoint) {
        StringBuilder signature = new StringBuilder();
        signature.append("(");

        // 获取参数类型
        org.aspectj.lang.reflect.MethodSignature methodSignature =
                (org.aspectj.lang.reflect.MethodSignature) joinPoint.getSignature();
        Class<?>[] parameterTypes = methodSignature.getParameterTypes();
        String[] parameterNames = methodSignature.getParameterNames();

        for (int i = 0; i < parameterTypes.length; i++) {
            if (i > 0) {
                signature.append(", ");
            }
            // 添加参数类型和参数名
            signature.append(parameterTypes[i].getSimpleName());
            if (parameterNames != null && i < parameterNames.length) {
                signature.append(" ").append(parameterNames[i]);
            } else {
                signature.append(" arg").append(i);
            }
        }

        signature.append(")");
        return signature.toString();
    }

    /**
     * 将参数转换为JSON字符串
     */
    private String convertArgsToJson(Object[] args) {
        if (args == null || args.length == 0) {
            return "无参数";
        }

        try {
            if (args.length == 1) {
                return objectMapper.writeValueAsString(args[0]);
            }
            return objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            log.warn("参数转换为JSON失败", e);
            return Arrays.toString(args);
        }
    }

    /**
     * 将结果转换为JSON字符串
     */
    private String convertResultToJson(Object result) {
        if (result == null) {
            return "无返回值";
        }

        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            log.warn("结果转换为JSON失败", e);
            return result.toString();
        }
    }
}
