package com.Ivoyant.GSJobplugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Ivoyant.GSJobplugin", "org.Ivoyant.service"})
public class GSJobpluginApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		SpringApplication.run(GSJobpluginApplication.class, args);
	}

}
