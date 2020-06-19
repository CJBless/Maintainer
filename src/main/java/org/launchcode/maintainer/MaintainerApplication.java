package org.launchcode.maintainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.launchcode.maintainer")
@EntityScan("org.launchcode.maintainer.models")
@EnableJpaRepositories("org.launchcode.maintainer.models.data")
public class MaintainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintainerApplication.class, args);
	}

}
