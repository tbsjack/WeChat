package com.ourhome.as.service;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService
{
    String processRequest(HttpServletRequest request);
}
