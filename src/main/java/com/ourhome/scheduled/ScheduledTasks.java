package com.ourhome.scheduled;

import com.ourhome.as.util.AccessTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
@Slf4j
public class ScheduledTasks
{
    /**
     * 
    * @Title: getAccessTokenTask 
    * @Description: 每查询2小时accessToken
    * @param
    * @return void    返回类型 
    * @throws
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 2, initialDelay = 1000)
    public void getAccessTokenTask()
    {
        String token = "";
        try
        {
            token = AccessTokenUtil.getToke();
        } catch (Exception e)
        {
            log.error(e.getMessage(),e);
        }
        log.info("Scheduling GetAccessToken Tasks, token: " + token);
    }

//    // 每1分钟执行一次
//    @Scheduled(cron = "* 0/2 * * * * ")
//    public void reportCurrentByCron()
//    {
//        log.info("Scheduling Tasks Examples By Cron: The time is now ");
//    }

}
