package by.tasktracker.web.controllers;

import by.tasktracker.config.MessagesBundle;
import by.tasktracker.config.MessagesBundle.Lang;

/**
 * Контроллер для загрузки языков для каждого пользователя.
 */
public class MessagesController extends MainController {

	/**
	 * Смена языка. По запросу пользователя загружаем файл с запрошенным
	 * языком и кладем объект в сессию.
	 */
	public void change() {
		String lang = param("lang");
		MessagesBundle mb = new MessagesBundle();
		if (lang.equals("RU")) {
			mb.setLanguage(Lang.RU);
		} else if (lang.equals("EN")) {
			mb.setLanguage(Lang.EN);
		}
		session("messages", mb);
		redirect("/");
	}
	
}
