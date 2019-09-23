package com.bankapp.business.app;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@SpringBootApplication
@RestController
public class BusinessappApplication implements CommandLineRunner {
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BusinessappApplication.class);
	}

*/

	public static final String SWIFT_BASE_URL = "https://sandbox.swift.com/swiftref-api";
	public static final String TRULIO_BASE_URL = "https://sandbox.swift.com/swiftref-api";
	private static final Logger logger = LoggerFactory.getLogger(BusinessappApplication.class);

	@Value("${spring.application.name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(BusinessappApplication.class, args);

	}



	@RequestMapping(value = "/getIBanValidity/{iban}", method = RequestMethod.GET)
	 public String getIbanValidity(@PathVariable("iban") String iban)  throws IOException {
	//public String getIbanValidity() {
       OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().addHeader("x-api-key","oHYsiDXR2OUbROdRXCe7W3WTvLhA9ERE")
                .url(SWIFT_BASE_URL + "/ibans/"+ iban + "/validity")
                .build();


            Call call = client.newCall(request);
            Response response = call.execute();

            return response.body().string();


        }




	/*@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("Hello World from Application Runner");
	}*/

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Hello world from Command Line Runner");
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello World from Tomcat" + name;
	}






}