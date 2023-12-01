package com.yoon.spring.jspTodoApp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private AuthenticationService authenticationService;
	
	public LoginController(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//login?name=Yoon
	@RequestMapping(value="login",method = RequestMethod.GET)
	public String gotoLoginPage() {
		//model.put("name", name);//ViewKey,Value
		//logger.debug("Requset Param is {}", name);
		logger.info("info level logger");
		logger.warn("warn level logger");
		//jsp/login.jsp
		return "login";
	}
	
	@RequestMapping(value="login",method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String name,@RequestParam String password, ModelMap model) {
		
		if (authenticationService.authenticate(name, password)) {
			model.put("name", name);
			return "welcome";
			
		}

		model.put("errorMassage", "Invalid Credentials! Please try again.");
		return "login";
	}
}
