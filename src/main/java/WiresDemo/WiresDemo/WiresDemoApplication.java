package WiresDemo.WiresDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WiresDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiresDemoApplication.class, args);
		log.info("Demo initialized!");
	}
}
