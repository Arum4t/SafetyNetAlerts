package com.safetynetalerts.webapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class WebappApplication implements ApplicationRunner {

	private static final Logger logger = LogManager.getLogger(WebappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run (ApplicationArguments applicationArguments){
		logger.debug("Debugging log");
		logger.info("Info log");
		logger.error("Oops! We have an Error. OK");
	}

}
