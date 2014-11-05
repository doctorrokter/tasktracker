package by.tasktracker.web.controllers;

import by.tasktracker.config.MessagesBundle;
import by.tasktracker.config.MessagesBundle.Lang;

public class MessagesController extends MainController {

	public void change() {
		String lang = param("lang");
		if (lang.equals("RU")) {
			MessagesBundle.setLanguage(Lang.RU);
			session("curr_lang", "ru");
		} else if (lang.equals("EN")) {
			MessagesBundle.setLanguage(Lang.EN);
			session("curr_lang", "en");
		}
		redirect("/");
	}
	
}
