package com.enuocms.boot.config.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 在页面中加入绝对路径
 * zhanxp@me.com
 */
public class ExtFreeMarkerView extends FreeMarkerView {

    private static final String CONTEXT_PATH = "base";
    @Override
    protected void exposeHelpers(Map<String, Object> model,
                                 HttpServletRequest request) throws Exception {
    	String base = request.getContextPath();
        model.put(CONTEXT_PATH, base);
        super.exposeHelpers(model, request);
    }
}
