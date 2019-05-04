package com.ourhome.as.util;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class HttpClientUtil
{
    /**
     * 
     * @Title: upload 
     * @Description: 通过Http Post请求上传文件 
     * @param @param url
     * @param @param filePath
     * @param @param serverFileName
     * @param @return 
     * @return String 
     * @throws
     */
    public static String upload(String url, String filePath, String serverFileName)
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try
        {
            // 设置超时
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000)
                    .build();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 指定待上传文件
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart(serverFileName, new FileBody(new File(filePath)));
            // 设置参数 key value
//            builder.addPart(key, new StringBody(""));
            httpPost.setEntity(builder.build());
            // 发送POST请求
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK)
            {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
                // 关闭流
                EntityUtils.consume(resEntity);
            }

        } catch (ClientProtocolException e)
        {
            log.error(e.getMessage(), e);
        } catch (IOException e)
        {
            log.error(e.getMessage(), e);
        } finally
        {
            HttpClientUtils.closeQuietly(httpClient);
            HttpClientUtils.closeQuietly(response);
        }
        return result;
    }
}
