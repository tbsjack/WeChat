package com.ourhome.as.util;

import java.util.Date;

import com.ourhome.bean.MessageText;
import com.ourhome.constant.Constant;
import com.thoughtworks.xstream.XStream;

public class TextMessageUtil implements BaseMessageUtil<MessageText>
{

    /**
     * 将发送消息封装成对应的xml格式
     */
    @Override
    public String messageToxml(MessageText message)
    {
        XStream xstream = new XStream();
        xstream.alias(Constant.XML, message.getClass());
        return xstream.toXML(message);
    }

    @Override
    public String initMessage(String fromUserName, String toUserName)
    {
        // 封装发送消息对象,封装时，需要将调换发送者和接收者的关系
        MessageText text = new MessageText();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setContent("平身...");
        text.setCreateTime(new Date().getTime());
        text.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
        return messageToxml(text);
    }
    /**
     * 
    * @Title: initMessage 
    * @Description: 初始化返回消息 
    * @param @param FromUserName
    * @param @param ToUserName
    * @param @param Content
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public String initMessage(String FromUserName, String ToUserName, String Content)
    {
        MessageText text = new MessageText();
        text.setToUserName(FromUserName);
        text.setFromUserName(ToUserName);
        text.setContent("您输入的内容是：" + Content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(Constant.RESP_MESSAGE_TYPE_TEXT);
        return messageToxml(text);
    }

}