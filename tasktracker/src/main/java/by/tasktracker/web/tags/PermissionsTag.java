package by.tasktracker.web.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.tasktracker.core.models.Role;
import by.tasktracker.core.models.User;

public class PermissionsTag extends TagSupport {

	private static final long serialVersionUID = -3872242682661874554L;
	private String rule;
	
	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
		User user = (User) req.getSession().getAttribute("logged_user");
		for (Role role : user.getRoles()) {
			if (role.getPermissions().contains(rule)) {
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}
	
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
}
