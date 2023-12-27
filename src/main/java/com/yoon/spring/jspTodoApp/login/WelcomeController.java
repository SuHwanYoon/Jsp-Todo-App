package com.yoon.spring.jspTodoApp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	private AuthenticationService authenticationService;
//	
//	public LoginController(AuthenticationService authenticationService) {
//		super();
//		this.authenticationService = authenticationService;
//	}
//
//	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	//login?name=Yoon
//	@RequestMapping(value="login",method = RequestMethod.GET)
//	public String gotoLoginPage() {
//		//model.put("name", name);//ViewKey,Value
//		//logger.debug("Requset Param is {}", name);
//		logger.info("info level logger");
//		logger.warn("warn level logger");
//		//jsp/login.jsp
//		return "login";
//	}
	
	//start use Spring Security
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	}
	
	//get input Id 
	private String getLoggedinUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
	
//	@RequestMapping(value="login",method = RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name,@RequestParam String password, ModelMap model) {
//		
//		if (authenticationService.authenticate(name, password)) {
//			model.put("name", name);
//			return "welcome";
//			
//		}
//
//		model.put("errorMassage", "Invalid Credentials! Please try again.");
//		return "login";
//	}
}
