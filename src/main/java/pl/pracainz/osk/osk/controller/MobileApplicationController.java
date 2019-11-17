package pl.pracainz.osk.osk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MobileApplicationController {

	
	@RequestMapping("/mobile/login")
	public boolean loginToMobileApp(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		return true;
	}
	
}
