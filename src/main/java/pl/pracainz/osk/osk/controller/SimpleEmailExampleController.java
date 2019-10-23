package pl.pracainz.osk.osk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class SimpleEmailExampleController {
 
    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail(@ModelAttribute("email") String email) {
 
    	String content = "Witaj! \nTwoje dane do logowania to:\n login: \n hasło: "; 
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         

        message.setTo(email);

        message.setSubject("[OSK] Dane do logowania");
        message.setText(content);
 
        // Send Message!
        this.emailSender.send(message);
 
        return "Email Sent!";
    }
 
}