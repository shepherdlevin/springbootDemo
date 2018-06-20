package com.levin.demo.common;


import com.levin.demo.task.DynamicScheduledTask;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserDynamicJob extends DynamicScheduledTask {

    private static Logger log = LoggerFactory.getLogger(DynamicScheduledTask.class);

    @Override
    public void doBiz() {
        Map<String, Object> params = new HashMap<>();
        try {
            log.info("做你想做的吧");
        } catch (Exception e) {
            log.error("Exception", e);
        }

    }

    @Scheduled(fixedDelay = 10000)
    public void printTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("task two " + format.format(new Date()));
    }

}
