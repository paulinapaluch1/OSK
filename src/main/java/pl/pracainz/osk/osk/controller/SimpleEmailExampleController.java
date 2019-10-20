package pl.pracainz.osk.osk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class SimpleEmailExampleController {
 
    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail() {
 
        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setTo("kmiecinc@gmail.com");
        message.setSubject("[OSK] Dane do logowania");
        message.setText("Witaj, Twoje dane do logowania to: login: , hasło: ");
 
        // Send Message!
        this.emailSender.send(message);
 
        return "Email Sent!";
    }
 
}