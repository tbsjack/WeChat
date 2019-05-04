package com.ourhome.as.util;

import java.io.IOException;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ourhome.bean.Image;
import com.ourhome.bean.ImageMessage;
import com.ourhome.constant.Constant;
import com.thoughtworks.xstream.XStream;
@Slf4j
public class ImageMessageUtil implements BaseMessageUtil<ImageMessage>
{
    @Override
    public String messageToxml(ImageMessage imageMessage)
    {
        XStream xStream = new XStream();
        xStream.alias(Constant.XML, imageMessage.getClass());
        xStream.alias(Constant.IMAGE, new Image().getClass());
        return null;
    }

    @Override
    public String initMessage(String fromUserName, String toUserName)
    {
        String defaultPath = "";
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(fromUserName);
        imageMessage.setToUserName(toUserName);
        imageMessage.setCreateTime(new Date().getTime());
        Image image = new Image();
        image.setMediaId(getMediaId(defaultPath));
        imageMessage.setImage(image);
        return null;
    }
    /**
     * 
     * @Title: initMessage 
     * @Description: 返回指定的图片 
     * @param @param fromUserName
     * @param @param toUserName
     * @param @param path
     * @param @return 
     * @return String 
     * @throws
     */
    public String initMessage(String fromUserName, String toUserName ,String path)
    {
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(fromUserName);
        imageMessage.setToUserName(toUserName);
        imageMessage.setCreateTime(new Date().getTime());
        Image image = new Image();
        image.setMediaId(getMediaId(path));
        imageMessage.setImage(image);
        return null;
    }
    /**
     * 
     * @Title: getMediaId 
     * @Description: 获取指定文件的mediaId
     * @param @param path
     * @param @return 
     * @return String 
     * @throws
     */
    public String getMediaId(String path)
    {
        String accessToken = AccessTokenUtil.getToke();
        String mediaId = null;
        try
        {
            mediaId = UpLoadUtil.upload(path, accessToken, "image");
        } catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return mediaId;
    } 
}
