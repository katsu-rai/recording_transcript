package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostRequest {

    public static void main(String[] args) throws IOException {

        HashMap<String, Object> carAttributes = new HashMap<>();
        carAttributes.put("year", 2019);
        carAttributes.put("price", 4999.99);
        carAttributes.put("color", "blue");
        Car car1 = new Car("BWM x5", carAttributes);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(car1);



        URL url = new URL("");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("X-API-Key", "669c94ad0ea94c51ba3b1d2322bb79b6");

        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
            dos.writeBytes(requestBody);
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        }


//        HashMap<String, Object> carAttributes = new HashMap<>();
//        carAttributes.put("year", 2019);
//        carAttributes.put("price", 4999.99);
//        carAttributes.put("color", "blue");
//        Car car1 = new Car("BWM x5", carAttributes);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper.writeValueAsString(car1);
//
//
//
//        URL url = new URL("https://api.restful-api.dev/objects");
//        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
//        conn.setRequestProperty("X-API-Key", "669c94ad0ea94c51ba3b1d2322bb79b6");
//
//        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
//            dos.writeBytes(requestBody);
//        }
//
//        try (BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//            String line;
//            while ((line = bf.readLine()) != null) {
//                System.out.println(line);
//            }
//        }
    }
}