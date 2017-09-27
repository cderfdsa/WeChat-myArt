package com.qk.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * Servlet Filter implementation class StrutsFilter
 */
public class StrutsFilter extends StrutsPrepareAndExecuteFilter {  
    public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) req;  
        //ï¿½ï¿½ï¿½ï¿½ï¿½Ëµï¿½url  
        String url = request.getRequestURI();  
        System.out.println(url);  
        if (url.contains("/jsp/controller.jsp")) {  
            System.out.println("Ê¹ÓÃ×Ô¶¨Òå¹ýÂËÆ÷");  
            chain.doFilter(req, res);  
        }else{  
            super.doFilter(req, res, chain);  
        }  
    }  
}  