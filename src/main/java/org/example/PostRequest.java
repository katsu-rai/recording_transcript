package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.net.http.HttpResponse.BodyHandlers;

public class PostRequest {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        Recording recording = new Recording();
        recording.setAudio_url("https://github.com/katsu-rai/recording_transcript/raw/master/src/main/java/org/example/recording/thisisryan.mp3");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(recording);

        System.out.println(jsonRequest);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "669c94ad0ea94c51ba3b1d2322bb79b6")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

        System.out.println(postResponse.body());

        recording = gson.fromJson(postResponse.body(), Recording.class);

        System.out.println(recording.getStatus());

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + recording.getId()))
                .header("Authorization", "669c94ad0ea94c51ba3b1d2322bb79b6")
                .build();

        while (true){
            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
            recording = gson.fromJson(getResponse.body(), Recording.class);

            System.out.println(recording.getStatus());

            if("completed".equals(recording.getStatus()) || "error".equals(recording.getStatus())){
                break;
            }
            Thread.sleep(1000);
        }

        System.out.println("Transcript completed!");
        System.out.println(recording.getText());


    }
}