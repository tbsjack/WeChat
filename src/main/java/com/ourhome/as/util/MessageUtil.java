package com.ourhome.as.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageUtil {
	private static Logger log = LoggerFactory.getLogger(MessageUtil.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> xml2Map(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream is = null;
		try {
			is = request.getInputStream();
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (DocumentException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
					log.error(e.getMessage(), e);
				}
			}
		}

		return map;
	}
}
