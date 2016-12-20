package com.hand.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(filterName="/LoginFilter",value="/jsp/*")
public class LoginFilter implements Filter {

   
    public LoginFilter() {
        
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest)request;
		if(request2.getSession().getAttribute("user") == null){
			request2.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
