package by.tasktracker.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tasktracker.web.controllers.MainController;

/**
 * Servlet Filter implementation class TaskTrackerFilter
 */
public class TaskTrackerFilter implements Filter {

	private String[] exc;
	private String encoding;
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse resp = (HttpServletResponse) response;
//    	req.setCharacterEncoding(encoding);
    	req.setCharacterEncoding(encoding);
    	    	
    	String url = req.getRequestURI().replaceFirst(req.getContextPath(), "");
    	System.out.println(url);
    	
    	boolean resourcesRequest = false;
    	for (String exclusion : exc) {
    		if (url.endsWith("." + exclusion)) {
    			resourcesRequest = true;
    		}
    	}
    	
    	if (resourcesRequest) {
    		chain.doFilter(request, response);
    	} else {
    		MainController mainC = new MainController(req, resp, chain);
    		mainC.getResourse();
    	}
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		exc = filterConfig.getInitParameter("exclusions").split(",");
		encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub	
	}

	
}
