//package com.bankapp.business.controller;
//
//
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.bankapp.business.model.Product;
//
//
//
//
//@RestController
//public class ProductController {
//    private static Map<String, Product> productRepo = new HashMap<>();
//    public static final String SWIFT_BASE_URL = "https://sandbox.swift.com/swiftref-api";
//    public static final String TRULIO_BASE_URL = "https://sandbox.swift.com/swiftref-api";
//
//
//    @RequestMapping(value = "/getIbanValidity")
//    @ResponseBody
//    //@RequestMapping(value = "/getIBanValidity/{iban}", method = RequestMethod.GET)
//    // public String getIbanValidity(@PathVariable("iban") String iban)  throws IOException {
//    public String getIbanValidity() {
//       /* OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(SWIFT_BASE_URL + "/ibans/"+ iban + "/validity")
//                .build();
//
//
//            Call call = client.newCall(request);
//            Response response = call.execute();
//
//            return response.body().string();
//
//
//        }*/
//
//        return "Hello";
//    }
//}