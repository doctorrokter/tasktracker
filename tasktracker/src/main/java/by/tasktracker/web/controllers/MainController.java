package by.tasktracker.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.tasktracker.core.util.Reflector;

public class MainController {
	
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static FilterChain chain;
	private static int id;
		
	public MainController() {
		
	}
	
	public MainController(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		MainController.request = request;
		MainController.response = response;
		MainController.chain = chain;
	}

	protected void param(String paramName, Object paramValue) {
		request.setAttribute(paramName, paramValue);
	}
	
	protected String param(String paramName) {
		return request.getParameter(paramName);
	}
	
	protected String[] paramArray(String paramName) {
		return request.getParameterValues(paramName);
	}
	
	protected void session(String sessionParam, Object paramValue) {
		request.getSession().setAttribute(sessionParam, paramValue);
	}
	
	protected Object session(String sessionParam) {
		return request.getSession().getAttribute(sessionParam);
	}
	
	protected void forward(String forwardTo) {
		try {
			request.getRequestDispatcher(forwardTo).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void redirect(String redirectTo) {
		try {
			response.sendRedirect(request.getContextPath() + redirectTo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doFilter() {
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	protected void error(String message) {
		try {
			response.sendError(404, message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected HttpServletRequest getRequest() {
		return request;
	}
	
	protected HttpServletResponse getResponse() {
		return response;
	}
	
	protected int getId() {
		return id;
	}
	
	protected void setId(int id) {
		MainController.id = id;
	}
	
	public String getResourse() {
		String url = request.getRequestURI().replaceFirst(request.getContextPath(), "");
		
		if (url.equals("/") && session("logged_user") == null) {
			doFilter();
		} else if (url.equals("/") && session("logged_user") != null) {
			redirect("/tasks");
		} else  if (url.startsWith("/login")) {
			if (url.endsWith("/auth")) {
				new LoginController().auth();
			} else {
				redirect("/");
			}
		} else if (url.startsWith("/messages")) {
			processUrl(url);
		} else if (session("logged_user") == null) {
			redirect("/");
		} else {
			processUrl(url);
		}
		return null;
	}
	
	private void processUrl(String url) {
		try {
			String[] dividedPath = url.replaceFirst("/", "").split("/");
			Map<String, String> map = toMap(dividedPath);
			Class<?> clazz = Class.forName("by.tasktracker.web.controllers." + map.get("controller"));
			Object controller = clazz.newInstance();
			Object[] methodParams = null;
			Class<?>[] classParams = null;
			if (map.get("method") != null) {
				controller.getClass().getMethod(map.get("method"), classParams).invoke(controller, methodParams);
			} else {
				controller.getClass().getMethod("index", classParams).invoke(controller, methodParams);
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect("/");
		}
	}
	
	private Map<String, String> toMap(String[] dividedPath) {
		Map<String, String> map = new HashMap<>();
		map.put("directory", dividedPath[0]);
		map.put("controller", Reflector.firstUpperCase(dividedPath[0]) + "Controller");
		if (dividedPath.length == 2) {
			map.put("method", dividedPath[1]);
		} else if (dividedPath.length == 3) {
			map.put("method", dividedPath[1]);
			setId(Integer.parseInt(dividedPath[2]));
		}
		return map;
	}
}
