package pl.pracainz.osk.osk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication   //(scanBasePackages= {"pl.praca.inz.osk"})
public class OskApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OskApplication.class, args);
	}

	
}
