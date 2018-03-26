package com.lxy.spring.cloud.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    private static final String TAG = "AccessFilter";
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    private static final String[] extraPath = {"/zuul/**","/api-user/**"};

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.getResponse().setCharacterEncoding("UTF-8");
        requestContext.getResponse().setHeader("Content-Type", "application/json; charset=utf-8");
        requestContext.getResponse().setContentType("application/json;charset=utf-8");
        HttpServletRequest request = requestContext.getRequest();
        log.info(String.format("%s request to %s", new Object[]{request.getMethod(), request.getRequestURI()}));
        String visitPath = request.getRequestURI();
        Object accessToken = request.getParameter("token");
        if (null == accessToken) {
            log.warn("token is empty!");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("对不起，您没有访问权限！");
            requestContext.setResponseStatusCode(401);
            return null;
        }
        if (isExistPath(visitPath)) {
            return null;
        }
        return null;
    }

    private boolean isExistPath(String visitPath) {
        for (String path : extraPath) {
            if (path.contains("*")) {
                String curPath = path.substring(0, path.indexOf("*"));
                if (visitPath.startsWith(curPath))
                    return true;
            } else {
                return path.equals(visitPath);
            }

        }
        return false;
    }
}