package com.enuocms.web.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhanxiaoping on 2017/8/28.
 * zhanxp@me.com
 */
public class ExtFreeMarkerView extends FreeMarkerView {

    private static final String CONTEXT_PATH = "base";
    //private static final String HOST_NAME = "sitehost";
    
    @Override
    protected void exposeHelpers(Map<String, Object> model,
                                 HttpServletRequest request) throws Exception {
    	String base = request.getContextPath();
        model.put(CONTEXT_PATH, base);
        //model.put(HOST_NAME, CommonConstants.FRONT_SERVER);
        super.exposeHelpers(model, request);
    }
}
