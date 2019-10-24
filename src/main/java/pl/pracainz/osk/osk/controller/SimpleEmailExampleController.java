package pl.pracainz.osk.osk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.User;
 
@Controller
public class SimpleEmailExampleController {
	private UserRepository userRepository;
	private StudentRepository studentRepository;
	private PasswordEncoder encoder;
    @Autowired
    public JavaMailSender emailSender;
 
    @ResponseBody
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail(@ModelAttribute("email") String email, @ModelAttribute("login") String login) {

    	//User user = userRepository.getOne(new Long(13));
    	//User user = userRepository.findByUsername("student3");
    	//Student theStudent = studentRepository.getOne(2);

    	//String password = encoder.encode(user.getPassword());
    	//String login = theStudent.getLogin();
    	String password = "haslo";
    	String content = "Witaj! \nTwoje dane do logowania to:\nlogin: " + login + "\nhasło: " + password; 

    	SimpleMailMessage message = new SimpleMailMessage();
         

        message.setTo(email);
        message.setSubject("[OSK] Dane do logowania");
        message.setText(content);
 

        this.emailSender.send(message);
 
        return "redirect:/students/list";
    }
 
}