package com.example.test.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/8/13 5:35 下午
 */
@Configuration
@Component
@EnableScheduling
public class ScheduledTask {
    private static Logger logger = Logger.getLogger(String.valueOf(ScheduledTask.class));

    public void sayHello() {
        logger.info("Hello world, i'm the king of the world!!!");
    }
}
