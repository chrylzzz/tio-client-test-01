package com.sdsoon;

import com.sdsoon.client.ClientStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TioClientTest01Application {

	public static void main(String[] args) throws Exception {

		ClientStarter.start();



		SpringApplication.run(TioClientTest01Application.class, args);
	}

}
