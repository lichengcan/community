package com.example.community.aspect;


import cn.hutool.crypto.SecureUtil;
import com.example.community.redis.RedisRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.TimeUnit;

/**
 * @data 2022/10/25 15:32
 * @description 定义一个防止重复提交切面
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAop {

    @Autowired
    private RedisRepository redisRepository;

    public RedisRepository getRedisRepository() {
        return redisRepository;
    }

    public void setRedisRepository(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    private static final String UNKNOWN = "unknown";

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.example.community.annotation.NoRepeatSubmit)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object arround(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        final String remoteAddr = getRemoteAddr(request);
        //这里是唯一标识 根据情况而定
        String key = remoteAddr + "-" + request.getServletPath();
        // 将key的长度变短一点3
        key = SecureUtil.md5(key);
        // 如果缓存中有这个url视为重复提交
        if (!redisRepository.haskey(key)) {
            //通过，执行下一步
            Object o = joinPoint.proceed();
            //然后存入redis 并且设置1s倒计时
            redisRepository.setCacheObject(key, "0", 1, TimeUnit.SECONDS);
            //返回结果
            return o;
        } else {
            throw new Exception();
        }

    }

    /**
     * 获取客户端IP地址
     * @param request 请求
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtils.split(ip, ",")[0];
    }

}
