package com.enuocms.boot.util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhanxiaoping on 2020/3/8.
 * zhanxp@me.com
 */

@Slf4j
public class ResponseUtil {
    /**
     * 返回响应数据
     *
     * @param httpServletResponse
     *            http响应
     * @param data
     *            数据类
     */
    public static void outData(HttpServletResponse httpServletResponse,
                               Object data) {
        outData(httpServletResponse, JSON.toJSONString(data));
    }

    /**
     * 返回响应数据
     *
     * @param httpServletResponse
     *            http响应
     * @param jsonString
     *            json字符串
     */
    public static void outData(HttpServletResponse httpServletResponse,
                               String jsonString) {
        try {
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setHeader("Content-type",
                    "text/html;charset=utf-8");
            PrintWriter writer = httpServletResponse.getWriter();
            if (StringUtil.isNotEmpty(jsonString)) {
                httpServletResponse.setHeader("Content-Length",
                        String.valueOf(jsonString.getBytes("utf-8").length));
            }
            writer.print(jsonString);
            log.info("outData success--------------------:"+jsonString);
            httpServletResponse.flushBuffer();
            // 无需flush和close，Tomcat会帮我处理
        } catch (IOException e) {
            log.error("outData error------------------:", e);
        }
    }
}
