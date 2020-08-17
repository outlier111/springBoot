package com.hqyj.javaSpringBoot.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;


@WebFilter(filterName = "requestParamFilter", urlPatterns = "/**")
public class RequestParamFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("=======Init Request Param Filter=======");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.debug("=======DO Request Param Filter=======");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
//        parameterMap.put("paramKey",new String[]{"***"});

        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest){
            @Override
            public String getParameter(String name) {
                String value = httpServletRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = servletRequest.getParameterValues(name);
                if (values !=null && values.length>0){
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("fuck","***");
                    }
                return values;
                }
                return super.getParameterValues(name);
            }

        };

        filterChain.doFilter(wrapper,servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.debug("=======Destroy Request Param Filter=======");
    }
}
