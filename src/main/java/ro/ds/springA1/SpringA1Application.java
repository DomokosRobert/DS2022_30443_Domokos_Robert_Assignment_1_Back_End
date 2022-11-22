package ro.ds.springA1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;
import ro.ds.springA1.controllers.CustomerController;

@SpringBootApplication
@Validated
public class SpringA1Application {


	public static void main(String[] args) {

		SpringApplication.run(SpringA1Application.class, args);
	}

}
