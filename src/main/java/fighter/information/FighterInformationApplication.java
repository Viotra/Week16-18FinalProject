package fighter.information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* My pom file has Spring JPA, Spring Web Tools, MySQL Connector J, and Lombok only*/
//Data-source is set to update and SQL init is set to never in YAML file.


@SpringBootApplication
public class FighterInformationApplication {
	public static void main(String[] args) {
		SpringApplication.run(FighterInformationApplication.class, args);
	}
}
