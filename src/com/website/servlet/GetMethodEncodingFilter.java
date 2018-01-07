package com.website.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest; 

/**
* 作者：hua'er
* 日期：2017年5月27日
*/
public class GetMethodEncodingFilter implements Filter {  
	  
    private String charset = "utf-8";  
      
    @Override  
    public void destroy() {  
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {  
          
        HttpServletRequest req = (HttpServletRequest)request;    
  
        req = new GetHttpServletRequestWrapper(req,charset);    
        filterChain.doFilter(req, response);    
    }  
  
    @Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
    }  
} 
