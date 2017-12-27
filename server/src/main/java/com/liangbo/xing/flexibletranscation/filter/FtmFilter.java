package com.liangbo.xing.flexibletranscation.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 17/12/26 下午12:23 xingliangbo Exp $
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class FtmFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = cast(servletRequest);
        HttpServletResponse httpServletResponse = cast(servletResponse);

    }

    @Override
    public void destroy() {

    }

    protected HttpServletRequest cast(ServletRequest req) {
        return (HttpServletRequest) req;
    }

    protected HttpServletResponse cast(ServletResponse resp) {
        return (HttpServletResponse) resp;
    }
}
