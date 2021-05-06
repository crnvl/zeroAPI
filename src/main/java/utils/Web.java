package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import test.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class Web {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static JSONObject post(String POST_URL, String POST_PARAMS) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);
        httpURLConnection.addRequestProperty("content-type", "application/json");

        httpURLConnection.setDoOutput(true);
        OutputStream os = httpURLConnection.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();

        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            JSONObject response = new JSONObject();

            while ((inputLine = in.readLine()) != null) {
                response = new JSONObject(inputLine);
            }
            in.close();
            return response;
        } else {
            Main.client.reconnect();
        }
        return new JSONObject("{\"code\":400}");
    }

    public static JSONArray get(String GET_URL) throws IOException {
        JSONArray result = new JSONArray();
        URL url = new URL(GET_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (var reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result = new JSONArray(line);
            }
        }
        return new JSONArray(result);
    }

    public static void put(String PUT_URL, String PUT_PARAMS) throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(PUT_URL))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(PUT_PARAMS))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        new JSONObject(response.body());
    }

    public static void patch(String PATCH_URL, String PATCH_PARAMS) throws IOException {
        URL url = new URL(PATCH_URL);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        byte[] out = PATCH_PARAMS.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        http.disconnect();
    }

}
