package by.tasktracker.web.controllers;

import by.tasktracker.config.MessagesBundle;
import by.tasktracker.config.MessagesBundle.Lang;

public class MessagesController extends MainController {

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
