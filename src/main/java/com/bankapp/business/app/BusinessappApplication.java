package com.bankapp.business.app;



import com.bankapp.business.model.ChargeAmount;
import com.bankapp.business.model.PaymentEvent;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class BusinessappApplication {

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
	public String getIbanValidity(@PathVariable("iban") String iban) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-api-key", "oHYsiDXR2OUbROdRXCe7W3WTvLhA9ERE")
				.url(SWIFT_BASE_URL + "/ibans/" + iban + "/validity")
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		return response.body().string();


	}

	@CrossOrigin
	@RequestMapping(value = "/api/getcountrycodes", method = RequestMethod.GET)
	public Map getCountryCodes() throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key", "f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "SIBOS")
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
	public String getRecommendedFields(@PathVariable("cc") String cc) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key", "f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "SIBOS")
				.url(TRULIOO_BASE_URL + "recommendedfields/Identity Verification/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();

		return response.body().string();


	}

	@CrossOrigin
	@RequestMapping(value = "/api/getcountrysubdivisions/{cc}", method = RequestMethod.GET)
	public String getCountrySubdivisions(@PathVariable("cc") String cc) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key", "f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "SIBOS")
				.url(TRULIOO_BASE_URL + "countrysubdivisions/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
//		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//		return jsonObject.toString();
		return response.body().string();

	}


	@CrossOrigin
	@RequestMapping(value = "/api/getdetailedconsents/{cc}", method = RequestMethod.GET)
	public String getDetailedConsents(@PathVariable("cc") String cc) throws IOException {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().addHeader("x-trulioo-api-key", "f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type", "application/json")
				.addHeader("User-Agent", "SIBOS")
				.url(TRULIOO_BASE_URL + "detailedConsents/Identity Verification/" + cc)
				.build();

		Call call = client.newCall(request);
		Response response = call.execute();
//		JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//		return jsonObject.toString();
		return response.body().string();

	}


	//curl -X POST "https://gateway.trulioo.com/trial/verifications/v1/verify" -H "accept: application/json" -H "Content-Type: application/json" -H "x-trulioo-api-key: 26f4c53b1665d9727177636e6ddfacfe" -d "{\"AcceptTruliooTermsAndConditions\":true,\"CleansedAddress\":false,\"ConfigurationName\":\"Identity Verification\",\"ConsentForDataSources\":[\"Visa Verification\"],\"CountryCode\":\"AU\",\"DataFields\":{\"PersonInfo\":{\"FirstGivenName\":\"J\",\"FirstSurName\":\"Smith\",\"MiddleName\":\"Henry\",\"DayOfBirth\":5,\"MonthOfBirth\":3,\"YearOfBirth\":1983,\"MinimumAge\":0},\"Location\":{\"BuildingNumber\":\"10\",\"PostalCode\":\"3108\",\"StateProvinceCode\":\"VIC\",\"StreetName\":\"Lawford\",\"StreetType\":\"St\",\"Suburb\":\"Doncaster\",\"UnitNumber\":\"3\"},\"Communication\":{\"EmailAddress\":\"testpersonAU%40gdctest.com\",\"Telephone\":\"03 9896 8785\"},\"Passport\":{\"Number\":\"N1236548\"}}}"

	@CrossOrigin
	@RequestMapping(value = "/api/verify", method = RequestMethod.POST)
	public String apiVerify(@org.springframework.web.bind.annotation.RequestBody String data) throws IOException {

		OkHttpClient client = new OkHttpClient();
		//String data = "{\"AcceptTruliooTermsAndConditions\":true,\"CleansedAddress\":false,\"ConfigurationName\":\"Identity Verification\",\"ConsentForDataSources\":[\"Visa Verification\"],\"CountryCode\":\"AU\",\"DataFields\":{\"PersonInfo\":{\"FirstGivenName\":\"J\",\"FirstSurName\":\"Smith\",\"MiddleName\":\"Henry\",\"DayOfBirth\":5,\"MonthOfBirth\":3,\"YearOfBirth\":1983,\"MinimumAge\":0},\"Location\":{\"BuildingNumber\":\"10\",\"PostalCode\":\"3108\",\"StateProvinceCode\":\"VIC\",\"StreetName\":\"Lawford\",\"StreetType\":\"St\",\"Suburb\":\"Doncaster\",\"UnitNumber\":\"3\"},\"Communication\":{\"EmailAddress\":\"testpersonAU%40gdctest.com\",\"Telephone\":\"03 9896 8785\"},\"Passport\":{\"Number\":\"N1236548\"}}}";
		RequestBody body = RequestBody.create(
				MediaType.parse("application/json; charset=utf-8"), data);
		Request request = new Request.Builder().addHeader("x-trulioo-api-key", "f8b19aa8eb1942f2eb840c1f097eb51f")
				.addHeader("Content-Type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("User-Agent", "SIBOS")
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


	@CrossOrigin
	@RequestMapping(value = "/acctPreval", method = RequestMethod.POST)
	public String acctPreval(@org.springframework.web.bind.annotation.RequestBody String data) throws IOException {

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(
				MediaType.parse("application/json; charset=utf-8"), data);
		Request request = new Request.Builder().addHeader("x-api-key", "yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B")
				.addHeader("LAUApplicationID", "001")
				.addHeader("LAUCallTime", "2018-03-23T15:5")
				.addHeader("LAURequestNonce", "e802ab96-bb3a-4965-9139-5214b9f0f074")
				.addHeader("LAUSigned", "(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(x-bic=cclabeb0)")
				.addHeader("LAUVersion", "1.0")
				.addHeader("x-bic", "cclabeb0")
				.addHeader("LAUSignature", "Qvml0qyQ4HcViGDwWpes2A==")
				.addHeader("Content-Type", "application/json")
				.addHeader("accept", "application/json")
				.addHeader("User-Agent", "SIBOS")
				.url("https://sandbox.swift.com/swift-preval-pilot/v1/accounts/verification")
				.post(body)
				.build();
		Call call = client.newCall(request);
		Response response = call.execute();
		return response.body().string();

	}


	@CrossOrigin
	@RequestMapping(value = "/getCorpTransactions", method = RequestMethod.GET)
	public String getCorpTransactions() throws IOException {

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("https://sandbox.swift.com/swift-apitracker-for-corporates/v3/corporates/payments/transactions?from_date_time=2019-08-27T00:00:00.000Z&to_date_time=2019-08-30T23:59:59.000Z")
				.addHeader("Accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.addHeader("LAUApplicationID", "001")
				.addHeader("LAUVersion", "1.0")
				.addHeader("LAUCallTime", "2019-09-24T15:02:28.714Z")
				.addHeader("LAURequestNonce", "ba208e07-6be7-4bf2-8ab3-8bc7a91a0350")
				.addHeader("LAUSigned", "(ApplAPIKey=yVGhKiV5z1ZGdaqFXoZ8AiSA9n5CrY6B),(RBACRole=[FullViewer/Scope/cclabeb0])")
				.addHeader("LAUSignature", "KlBpETOFWouNmkG9oc7iDw==")
				.addHeader("x-api-key", "oHYsiDXR2OUbROdRXCe7W3WTvLhA9ERE")
				.addHeader("User-Agent", "SIBOS")
				.addHeader("Host", "sandbox.swift.com")
				.addHeader("Accept-Encoding", "gzip, deflate")
				.build();

		PaymentEvent t1p1 = new PaymentEvent("BANABEBB","BANBUS33","SHAR",new ChargeAmount("GBP","0"));
		PaymentEvent t1p2 = new PaymentEvent("BANBUS33","BANCDEFF","SHAR",new ChargeAmount("GBP","5"));
		PaymentEvent t1p3 = new PaymentEvent("BANCDEFF","","SHAR",new ChargeAmount("GBP","10"));

		List t1 = new ArrayList<PaymentEvent>();
		t1.add(t1p1);
		t1.add(t1p2);
		t1.add(t1p3);
		String recvdSVG = helperSVG(t1);

		Response response = client.newCall(request).execute();
		//JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
		//return jsonObject.getAsJsonArray().toString();
		//System.out.println(jsonObject.toString());
		//return response.body().string();

         return recvdSVG;


		// return response.body().string();

	}


	private String helperSVG(List<PaymentEvent> paymentlist){

		String element = "graph LR";
		String fromTo;
		String alteredSVG=null;
		alteredSVG = element;

		for (PaymentEvent pe : paymentlist) {


			 alteredSVG = alteredSVG +"\n" + pe.getFrom() + "--> " + pe.getTo() ;

		}

        return alteredSVG;
	}


}