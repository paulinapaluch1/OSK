package pl.pracainz.osk.osk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
 

import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.PasswordGenerator;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.User;
@Controller
public class SimpleEmailExampleController {

 

    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    UserRepository userRepository;
    
    
    @RequestMapping("/sendEmail")
    public String sendSimpleEmail(@ModelAttribute("email") String email, @RequestParam("id") int id) {
        Student student = studentRepository.getOne(id);
        String password = PasswordGenerator.generatePassword(20);
        User user  = userRepository.findById(student.getUserId());
        user.setPassword(encoder.encode(password));
		userRepository.save(user);
		
		String content = "Witaj " + student.getName() + "! \nTwoje dane do logowania to:\n login: " + student.getLogin() + " \n hasło: "+ password; 
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("[OSK] Dane do logowania");
        message.setText(content);
 
        this.emailSender.send(message);
		return "redirect:students/list";
    }
 
}