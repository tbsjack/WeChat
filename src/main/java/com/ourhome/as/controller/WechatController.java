package com.ourhome.as.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ourhome.as.service.WeChatService;
import com.ourhome.as.util.CheckUtil;

@RestController()
public class WechatController
{

    private static Logger log = LoggerFactory.getLogger(WechatController.class);
    @Autowired
    private CheckUtil checkUtil;
    @Autowired   
    private WeChatService weChatService;
    
    /**
     * 
     * @Title: connect
     * @Description:公众号服务器校验微信请求
     * @param @param signature 微信加密签名，结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param @param timestamp 时间戳
     * @param @param nonce 随机数
     * @param @param echostr 随机字符串
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    @GetMapping("weChat")
    public String connect(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr)
    {
        if (checkUtil.checkSignature(signature, timestamp, nonce))
        {
            log.info("[WeChat]Signature verification passes");
            // 如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else
        {
            log.info("[WeChat]Signature verification fail..");
            // 如果检验失败返回空字符串
            return "";
        }
    }

    /**
     * 
     * @Title: message
     * @Description: 微信服务器消息接收
     * @param request
     * @return String 返回类型
     * @throws
     */
    @PostMapping("weChat")
    public String message(HttpServletRequest request)
    {
        return weChatService.processRequest(request);
    }

}
