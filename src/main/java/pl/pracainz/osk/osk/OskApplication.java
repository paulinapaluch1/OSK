package pl.pracainz.osk.osk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

  
@SpringBootApplication
public class OskApplication {
	public static void main(String[] args) {
		
		SpringApplication.run(OskApplication.class, args);
	}

}
