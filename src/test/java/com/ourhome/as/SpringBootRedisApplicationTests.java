package com.ourhome.as;

import com.ourhome.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

    public static Logger logger = LoggerFactory.getLogger(SpringBootRedisApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisDao redisDao;
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testRedis() {
        redisDao.setKey("name", "cuikai");
        redisDao.setKey("age", "30");
        logger.info(redisDao.getValue("name"));
        logger.info(redisDao.getValue("age"));
    }
    @Test
    public void test() {
        System.out.println(messageSource.getMessage("key",null,Locale.CHINA));
    }
}
