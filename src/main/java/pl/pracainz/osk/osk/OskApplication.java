package pl.pracainz.osk.osk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication   //(scanBasePackages= {"pl.praca.inz.osk"})
public class OskApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OskApplication.class, args);
	}

	
}
