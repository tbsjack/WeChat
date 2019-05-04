package com.ourhome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeChatApplication
{
    private static Logger log = LoggerFactory.getLogger(WeChatApplication.class);
    public static void main(String[] args)
    {
        SpringApplication.run(WeChatApplication.class, args);
        log.info("[WeChat]Server starting...");
    }
}
