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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tasktracker.config.MessagesBundle;
import by.tasktracker.web.controllers.MainController;

/**
 * Основной фильтр, который принимает и обрабатывает все приходящие запросы.
 */
public class TaskTrackerFilter implements Filter {

	private static final Logger logger = Logger.getLogger(TaskTrackerFilter.class);
	private String[] exc;
	private String encoding;
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse resp = (HttpServletResponse) response;
    	req.setCharacterEncoding(encoding);
    	HttpSession session = req.getSession(true);
    	
    	if (session.getAttribute("messages") == null) {
    		session.setAttribute("messages", new MessagesBundle());
    	}
    	    	
    	String url = req.getRequestURI().replaceFirst(req.getContextPath(), "");
    	logger.debug(url);
    	
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
