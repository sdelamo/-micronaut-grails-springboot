package example.springboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = "eu.vies",
		basePackageClasses = SpringbootApplication.class)
@SpringBootApplication
class SpringbootApplication {

	static void main(String[] args) {
		SpringApplication.run(SpringbootApplication, args)
	}

}
