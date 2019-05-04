package com.ourhome.as.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.ourhome.as.util.MessageUtil;
import com.ourhome.as.util.TextMessageUtil;
import com.ourhome.constant.Constant;
@Component
public class WeChatServiceImpl implements WeChatService
{

    @Override
    public String processRequest(HttpServletRequest request)
    {
        // 将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xml2Map(request);
        String ToUserName = map.get(Constant.ToUserName);
        String FromUserName = map.get(Constant.FromUserName);
        String MsgType = map.get(Constant.MsgType);
        String Content = map.get(Constant.Content);

        String message = null;
        // 处理文本类型，实现输入1，回复相应的封装的内容
        if (Constant.REQ_MESSAGE_TYPE_TEXT.equals(MsgType))
        {
            TextMessageUtil textMessage = new TextMessageUtil();
            // TODO test用户输入1 
            if ("1".equals(Content))
            {
                message = textMessage.initMessage(FromUserName, ToUserName);
            } else
            {
                message = textMessage.initMessage(FromUserName, ToUserName, Content);
            }
        }
        return message;
    }

}
