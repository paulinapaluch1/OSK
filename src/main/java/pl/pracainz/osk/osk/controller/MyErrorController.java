package pl.pracainz.osk.osk.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class MyErrorController implements ErrorController  {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
 /*
    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
    */
   
}