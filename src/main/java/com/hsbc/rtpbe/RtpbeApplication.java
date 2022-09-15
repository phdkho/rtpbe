package com.hsbc.rtpbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RtpbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtpbeApplication.class, args);
	}

}
