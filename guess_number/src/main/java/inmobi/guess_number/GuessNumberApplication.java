package inmobi.guess_number;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GuessNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessNumberApplication.class, args);
	}

}
