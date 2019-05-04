package com.ourhome.as.util;


import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ourhome.bean.AccessToken;
import com.ourhome.constant.Constant;

import net.sf.json.JSONObject;
@Slf4j
public class AccessTokenUtil {
    private static final String APP_ID = "wxe015114188171b9e";
    private static final String APPSECRET = "31ef02c2cb1d701a1532f239f55b8c03";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static String token = "";

    /**
     * 
    * @Title: doGet 
    * @Description: 处理GET请求
    * @param @param url
    * @param @return    设定文件 
    * @return JSONObject    返回类型 
    * @throws
     */
    public static JSONObject doGet(String url)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        JSONObject jsonObj = null;
        try
        {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            if (null != entity)
            {
                String result = EntityUtils.toString(entity);
                if (null != result && !"".equals(result))
                {
                    // TODO 格式化有问题
                    jsonObj = JSONObject.fromObject(result);
                }
            }
        } catch (ClientProtocolException e)
        {
            log.error(e.getMessage(), e);
        } catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return jsonObj;
    }
    
    /**
     * 
    * @Title: doPost 
    * @Description: 发送post请求 
    * @param @param url 请求路径
    * @param @param outStr 请求参数
    * @param @return    设定文件 
    * @return JSONObject    返回类型 
    * @throws
     */
    public static JSONObject doPost(String url,String paramStr)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        JSONObject jsonObj = null;
        try
        {
            post.setEntity(new StringEntity(paramStr, "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            if (null != response)
            {
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                if (null != result && !"".equals(result))
                {
                    jsonObj = JSONObject.fromObject(result);
                }
            }
        } catch (ClientProtocolException e)
        {
            log.error(e.getMessage(), e);
        } catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return jsonObj;
    }
    /**
     * 
    * @Title: getAccessToken 
    * @Description: 通过请求个获取AccessToken
    * @param @return    设定文件 
    * @return AccessToken    返回类型 
    * @throws
     */
    public static AccessToken getAccessToken()
    {
        // System.out.println("从接口中获取");
        log.info("[WeChat]Get access token form request...");
        // Jedis jedis = RedisUtil.getJedis();
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace(Constant.APP_ID, APP_ID).replace(Constant.APPSECRET, APPSECRET);
        JSONObject json = doGet(url);
        if (json != null)
        {
            token.setAccessToken(json.getString("access_token"));
            token.setExpiresIn(json.getInt("expires_in"));
            // jedis.set("access_token", json.getString("access_token"));
            // jedis.expire("access_token", 60*60*2);
        }
        // RedisUtil.returnResource(jedis);
        return token;
    }
    /**
     * 
    * @Title: getToke 
    * @Description: 获取AccessToken
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public static String getToke()
    {
        if (null == token || "".equals(token))
        {
            token = getAccessToken().getAccessToken();
        }
        return token;
    }
}
