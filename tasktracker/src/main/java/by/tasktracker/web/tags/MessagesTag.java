package by.tasktracker.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.tasktracker.config.MessagesBundle;

public class MessagesTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1911603264081998198L;
	private String message;
		
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.println(MessagesBundle.message(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
