package com.bankapp.business.app;



import okhttp3.*;
import okhttp3.RequestBody;
import okio.BufferedSink;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class BusinessappApplication  {

	public static final String SWIFT_BASE_URL = "https://sandbox.swift.com/swiftref-api";
	public static final String TRULIOO_BASE_URL = "https://gateway.trulioo.com/trial/configuration/v1/";

	private static final Logger logger = LoggerFactory.getLogger(BusinessappApplication.class);

	@Value("${spring.application.name}")
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(BusinessappApplication.class, args);

	}

    @CrossOrigin
	@RequestMapping(value = "/getIBanValidity/{iban}", method = RequestMethod.GET)
	 public String getIbanValidity(@PathVariable("iban") String iban)  throws IOException {

		OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().addHeader("x-api-key","oHYsiDXR2OUbROdRXCe7W3WTvLhA9ERE")
                .url(SWIFT_BASE_URL + "/ibans/"+ iban + "/validity")
                .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            return response.body().string();


        }

    @CrossOrigin
	@RequestMapping(value = "/api/getcountrycodes", method = RequestMethod.GET)
	public Map getCountryCodes()  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "countrycodes/Identity Verification")
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		HashMap<String, String> map = new HashMap<>();
		map.put("response", response.body().string());
		return map;

	}

	@CrossOrigin
	@RequestMapping(value = "/api/getrecommendedfields/{cc}", method = RequestMethod.GET)
	public Map getRecommendedFields(@PathVariable("cc") String cc)  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "recommendedfields/Identity Verification/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
		HashMap<String, String> map = new HashMap<>();

		map.put("response", response.body().string());
		return map;


	}

	@CrossOrigin
	@RequestMapping(value = "/api/getcountrysubdivisions/{cc}", method = RequestMethod.GET)
	public Map getCountrySubdivisions(@PathVariable("cc") String cc)  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "countrysubdivisions/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
//		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//		return jsonObject.toString();
		HashMap<String, String> map = new HashMap<>();

		map.put("response", response.body().string());
		return map;

	}



	@CrossOrigin
	@RequestMapping(value = "/api/getdetailedconsents/{cc}", method = RequestMethod.GET)
	public Map getDetailedConsents(@PathVariable("cc") String cc)  throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("User-Agent","SIBOS")
				.url(TRULIOO_BASE_URL + "detailedConsents/Identity Verification/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
//		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//		return jsonObject.toString();
		HashMap<String, String> map = new HashMap<>();

		map.put("response", response.body().string());
		return map;

	}


	//curl -X POST "https://gateway.trulioo.com/trial/verifications/v1/verify" -H "accept: application/json" -H "Content-Type: application/json" -H "x-trulioo-api-key: 26f4c53b1665d9727177636e6ddfacfe" -d "{\"AcceptTruliooTermsAndConditions\":true,\"CleansedAddress\":false,\"ConfigurationName\":\"Identity Verification\",\"ConsentForDataSources\":[\"Visa Verification\"],\"CountryCode\":\"AU\",\"DataFields\":{\"PersonInfo\":{\"FirstGivenName\":\"J\",\"FirstSurName\":\"Smith\",\"MiddleName\":\"Henry\",\"DayOfBirth\":5,\"MonthOfBirth\":3,\"YearOfBirth\":1983,\"MinimumAge\":0},\"Location\":{\"BuildingNumber\":\"10\",\"PostalCode\":\"3108\",\"StateProvinceCode\":\"VIC\",\"StreetName\":\"Lawford\",\"StreetType\":\"St\",\"Suburb\":\"Doncaster\",\"UnitNumber\":\"3\"},\"Communication\":{\"EmailAddress\":\"testpersonAU%40gdctest.com\",\"Telephone\":\"03 9896 8785\"},\"Passport\":{\"Number\":\"N1236548\"}}}"

	@CrossOrigin
	@RequestMapping(value = "/api/verify", method = RequestMethod.POST)
	public String apiVerify(@org.springframework.web.bind.annotation.RequestBody String data)  throws IOException {

		OkHttpClient client = new OkHttpClient();
		//String data = "{\"AcceptTruliooTermsAndConditions\":true,\"CleansedAddress\":false,\"ConfigurationName\":\"Identity Verification\",\"ConsentForDataSources\":[\"Visa Verification\"],\"CountryCode\":\"AU\",\"DataFields\":{\"PersonInfo\":{\"FirstGivenName\":\"J\",\"FirstSurName\":\"Smith\",\"MiddleName\":\"Henry\",\"DayOfBirth\":5,\"MonthOfBirth\":3,\"YearOfBirth\":1983,\"MinimumAge\":0},\"Location\":{\"BuildingNumber\":\"10\",\"PostalCode\":\"3108\",\"StateProvinceCode\":\"VIC\",\"StreetName\":\"Lawford\",\"StreetType\":\"St\",\"Suburb\":\"Doncaster\",\"UnitNumber\":\"3\"},\"Communication\":{\"EmailAddress\":\"testpersonAU%40gdctest.com\",\"Telephone\":\"03 9896 8785\"},\"Passport\":{\"Number\":\"N1236548\"}}}";
		RequestBody body = RequestBody.create(
				MediaType.parse("application/json; charset=utf-8"), data);
		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type","application/json")
				.addHeader("accept","application/json")
				.addHeader("User-Agent","SIBOS")
				.url("https://gateway.trulioo.com/trial/verifications/v1/verify")
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
//		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//		return jsonObject.toString();
		return response.body().string();

	}


//	@RequestMapping(value = "/api/verify", method = RequestMethod.POST)
//	public String apiVerify(@org.springframework.web.bind.annotation.RequestBody BusinessDataDAO bd)  throws IOException {
//
//		OkHttpClient client = new OkHttpClient();
//		//String data = "{\"AcceptTruliooTermsAndConditions\":true,\"CleansedAddress\":false,\"ConfigurationName\":\"Identity Verification\",\"ConsentForDataSources\":[\"Visa Verification\"],\"CountryCode\":\"AU\",\"DataFields\":{\"PersonInfo\":{\"FirstGivenName\":\"J\",\"FirstSurName\":\"Smith\",\"MiddleName\":\"Henry\",\"DayOfBirth\":5,\"MonthOfBirth\":3,\"YearOfBirth\":1983,\"MinimumAge\":0},\"Location\":{\"BuildingNumber\":\"10\",\"PostalCode\":\"3108\",\"StateProvinceCode\":\"VIC\",\"StreetName\":\"Lawford\",\"StreetType\":\"St\",\"Suburb\":\"Doncaster\",\"UnitNumber\":\"3\"},\"Communication\":{\"EmailAddress\":\"testpersonAU%40gdctest.com\",\"Telephone\":\"03 9896 8785\"},\"Passport\":{\"Number\":\"N1236548\"}}}";
//		RequestBody body = RequestBody.create(
//				MediaType.parse("application/json; charset=utf-8"),bd);
//		Request request = new Request.Builder().addHeader("x-trulioo-api-key","f8b19aa8eb1942f2eb840c1f097eb51f")
//				.addHeader("Content-Type","application/json")
//				.addHeader("accept","application/json")
//				.addHeader("User-Agent","SIBOS")
//				.url("https://gateway.trulioo.com/trial/verifications/v1/verify")
//				.post(body)
//				.build();
//		Call call = client.newCall(request);
//		Response response = call.execute();
////		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
////		return jsonObject.toString();
//		return response.body().string();
//
//	}



}