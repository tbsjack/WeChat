package com.ourhome.as.util;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

public class HttpsClinetUtil  
{
    /**
     * This example demonstrates how to create secure connections with a custom SSL
     * context.
     */
    public static CloseableHttpClient getSSLClient()
    {
        CloseableHttpClient httpclient = null;
        // Trust own CA and all self-signed certs
        try
        {
            // apache官方示例
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new File("my.keystore"), "nopassword".toCharArray(), new TrustSelfSignedStrategy())
                    .build();
            // TODO 网友推荐待验证
            // SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
            // null,new TrustStrategy() {
            // //信任所有
            // public boolean isTrusted(X509Certificate[] chain, String authType)
            // throws CertificateException {
            // return true;
            // }
            // }).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]
            { "TLSv1" }, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyStoreException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (CertificateException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return httpclient;
    }
    
    public final static void main(String[] args) throws Exception
    {
        CloseableHttpClient httpclient = getSSLClient();
        try
        {
            HttpGet httpget = new HttpGet("https://httpbin.org/");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try
            {
                HttpEntity entity = response.getEntity();
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                EntityUtils.consume(entity);
            } finally
            {
                response.close();
            }
        } finally
        {
            httpclient.close();
        }
    }

}
