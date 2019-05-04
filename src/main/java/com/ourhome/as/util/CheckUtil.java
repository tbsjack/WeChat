package com.ourhome.as.util;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
@Slf4j
public class CheckUtil 
{
	/**
	 * 通信密文
	 */
	private final static String TOKEN = "cuikai";
	
	public boolean checkSignature(String signature,String timestamp,String nonce)
	{
		if(StringUtils.isEmpty(signature)||StringUtils.isEmpty(timestamp)||StringUtils.isEmpty(nonce))
		{
			log.info("[WeChat]Param is empty!");
			return false;
		}
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		String[] strArray = { TOKEN, timestamp, nonce };
		Arrays.sort(strArray);
		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}
		String sha1Str = DigestUtils.sha1Hex(sb.toString());
		// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (!StringUtils.isEmpty(sha1Str) && sha1Str.endsWith(signature)) {
			return true;
		} else {
			return false;
			
		}
	}

}
