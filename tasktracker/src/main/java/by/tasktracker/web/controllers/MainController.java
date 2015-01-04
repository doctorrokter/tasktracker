package by.tasktracker.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tasktracker.core.util.Reflector;

/**
 * Основной контроллер, инкапсулирующий всю логику работы с объектами HttpServletReuqest,
 * HttpServletResponse и FilterChain.
 */
public class MainController {
	
	public final String pagesLocation = "/WEB-INF/pages/";
	private static final Logger logger = Logger.getLogger(MainController.class);
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	private static FilterChain chain;
	private static int id;
	private static String defaultLayout;
		
	public MainController() {
		
	}
	
	public MainController(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		MainController.request = request;
		MainController.response = response;
		MainController.chain = chain;
		defaultLayout = "_default_layout.jsp";
	}

	/**
	 * Положить пару "ключ:значение" в объект HttpServletRequest.
	 * @param paramName ключ, по которому будем извлекать параметр из объекта запроса в дальнейшем.
	 * @param paramValue значение, соответствующее ключу.
	 */
	protected void param(String paramName, Object paramValue) {
		request.setAttribute(paramName, paramValue);
	}
	
	/**
	 * Извлечь из объекта HttpServletRequest значение по ключу.
	 * @param paramName ключ, по которому будем извлекать параметр из объекта запроса.
	 * @return значение, соответствующее ключу.
	 */
	protected String param(String paramName) {
		return request.getParameter(paramName);
	}
	
	/**
	 * Получить массив значений из объекта HttpServletRequest, соответствующих передаваемому ключу.
	 * @param paramName ключ, по которому будем извлекать параметр из объекта запроса.
	 * @return массив значений, соответствующих ключу.
	 */
	protected String[] paramArray(String paramName) {
		return request.getParameterValues(paramName);
	}
	
	/**
	 * Положить пару "ключ:значение" в объект HttpSession.
	 * @param sessionParam ключ, по которому будем извлекать параметр из объекта сессии.
	 * @param paramValue значение, соответствующее ключу.
	 */
	protected void session(String sessionParam, Object paramValue) {
		request.getSession().setAttribute(sessionParam, paramValue);
	}
	
	/**
	 * Извлечь из объекта HttpSession значение по ключу.
	 * @param sessionParam ключ, по которому будем извлекать параметр из объекта запроса.
	 * @return значение, соответствующее ключу.
	 */
	protected Object session(String sessionParam) {
		return request.getSession().getAttribute(sessionParam);
	}
	
	/**
	 * Инкапсуляция форварда. Передача параметров на view.
	 * @param forwardTo страница (jsp), куда передаем параметры. По-умолчанию это всегда _default_layout.jsp
	 */
	protected void forward(String forwardTo) {
		try {
			request.getRequestDispatcher(forwardTo).forward(request, response);
		} catch (ServletException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Инкапсуляция редиректа.
	 * @param redirectTo часть адреса ресурса, куда рекдиректить.
	 */
	protected void redirect(String redirectTo) {
		try {
			response.sendRedirect(request.getContextPath() + redirectTo);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * Инкапсуляция doFilter (на всякий случай).
	 */
	protected void doFilter() {
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (ServletException e) {
			logger.error(e.getMessage());
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
	
	public String getDefaultLayout() {
		return defaultLayout;
	}

	public void setDefaultLayout(String defaultLayout) {
		MainController.defaultLayout = defaultLayout;
	}

	/**
	 * Метод, разбирающий пришедший URL на составные части, создающий объекты других контроллеров
	 * и вызывающий их методы.
	 * Например, URL вида http://localhost/tasktracker/tasks/info/1 означает, что у нас есть контроллер
	 * с названием TasksController (часть /tasks/ нашего URL), у этого контроллера есть метод info 
	 * (часть /info/), а 1 в конце URL означает id искомого ресурса в базе данных (в данном случае, это
	 * сущность task, у которой id = 1).
	 * @return
	 */
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
				processUrl(url);
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
	
	/**
	 * Вся магия происходит тут. Использовано такое свойство в Java как Reflection.
	 * В некотором роде можно рассматривать такой подход создания контроллеров и вызова 
	 * их методов заменой паттерну Command. Примененный подход позволит сократить количество
	 * кода, написания блоков if/else и т.д., так как сам URL и выступает в роли команды.
	 * @param url
	 */
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
			logger.error(e.getMessage());
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
