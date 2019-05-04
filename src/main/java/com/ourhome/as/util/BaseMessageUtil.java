package com.ourhome.as.util;

/**
 * 
 * @author Administrator
 *
 * @param <T>
 */
public interface BaseMessageUtil<T>
{
    /**
     * 将回复的信息对象转xml格式给微信
     * 
     * @param message
     * @return
     */
    public String messageToxml(T t);

    /**
     * 回复的信息封装
     * 
     * @param fromUserName
     * @param toUserName
     * @param Content
     * @return
     */
    public String initMessage(String fromUserName, String toUserName);
}