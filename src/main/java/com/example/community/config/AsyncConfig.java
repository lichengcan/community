package com.example.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 设置核心线程数
        executor.setMaxPoolSize(10); // 设置最大线程数
        executor.setQueueCapacity(25); // 设置队列容量
        executor.setThreadNamePrefix("Async-"); // 设置线程名称前缀
        executor.initialize();
        return executor;
    }
}
