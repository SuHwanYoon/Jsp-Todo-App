package com.yoon.spring.jspTodoApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//login?name=Yoon
	@RequestMapping("login")
	public String gotoLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);//Key,Value
		logger.debug("Requset Param is {}", name);
		logger.info("info level logger");
		logger.warn("warn level logger");
		//jsp/login.jsp
		return "login";
	}
}
