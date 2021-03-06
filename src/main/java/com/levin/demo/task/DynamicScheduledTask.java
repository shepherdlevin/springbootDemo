package com.levin.demo.task;


import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

public class DynamicScheduledTask implements SchedulingConfigurer {

    @Value("${cron.pattern.test}")
    public String pattern;

    private static Logger log = LoggerFactory.getLogger(DynamicScheduledTask.class);

    public void setCron(String cron) {
        this.pattern = cron;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                doBiz();
            }

        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //定时任务触发，可修改定时任务的执行周期
                CronTrigger trigger = new CronTrigger(pattern);
                Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });

    }

    public void doBiz() {
        log.info("task start");
    }

}

