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

	public static final String SWIFT_BASE_URL = "https://sandbox.swift.com/swiftref-api";
	public static final String TRULIOO_BASE_URL = "https://gateway.trulioo.com/trial/configuration/v1/";
	private static final Logger logger = LoggerFactory.getLogger(BusinessappApplication.class);

	@Value("${spring.application.name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(BusinessappApplication.class, args);

	}


	@RequestMapping(value = "/getIBanValidity/{iban}", method = RequestMethod.GET)
	 public String getIbanValidity(@PathVariable("iban") String iban)  throws IOException {

		OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().addHeader("x-api-key","123")
                .url(SWIFT_BASE_URL + "/ibans/"+ iban + "/validity")
                .build();


            Call call = client.newCall(request);
            Response response = call.execute();

            return response.body().string();


        }


	@RequestMapping(value = "/countrycodes", method = RequestMethod.GET)

	public String getCountryCodes()  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "countrycodes/Identity Verification")
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		return response.body().string();


	}


	/*@RequestMapping(value = "/api/getrecommendedfields/{cc}", method = RequestMethod.GET)

	https://gateway.trulioo.com/trial/configuration/v1/recommendedfields/Identity%20Verification/AU
	@RequestMapping(value = "/getIBanValidity/{iban}", method = RequestMethod.GET)
	public String getCountryCodes()  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "countrycodes/Identity Verification")
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		return response.body().string();


	}



			/api/getrecommendedfields/:countryCode

2:19 PM
/api/getcountrysubdivisions/:countryCode
2:19 PM
/api/getdetailedconsents/:countryCode  */


	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("Hello world from Command Line Runner");
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello World from Tomcat" + name;
	}








}