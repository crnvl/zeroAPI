package api;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.Web;

import java.io.IOException;
import java.util.Date;

public class Request {

    private static final String BASE_URL = "http://161.97.165.1:8080/api/v2";
    private static final String ROOMS = "/rooms";
    private static final String USERS = "/users";
    private static final String MESSAGES = "/messages";

    public static class Users {
        public static JSONObject postLogin(String username) throws IOException {
            JSONObject obj = new JSONObject();
            obj.put("name", username);
            return Web.post(BASE_URL + USERS, obj.toString());
        }

        public static void putChangeUsername(String username, String UUID) throws IOException, InterruptedException {
            JSONObject obj = new JSONObject();
            obj.put("name", username);
            obj.put("id", UUID);
            Web.put(BASE_URL + USERS, obj.toString());
        }

        public static JSONArray getUsers(String roomId) throws IOException {
            return Web.get(BASE_URL + ROOMS + "/" + roomId + USERS);
        }

        public static void patchActive(String roomId, String UUID) throws IOException {
            Web.patch(BASE_URL + ROOMS + "/" + roomId + USERS + "/active/" + UUID, "");
        }
    }

    public static class Messages {
        public static JSONArray getHistory(String roomId, int amount) throws IOException {
            return Web.get(BASE_URL + ROOMS + "/" + roomId + MESSAGES + "/latest?amount=" + amount);
        }

        public static JSONArray getHistoryBefore(String roomId, String messageId, int amount) throws IOException {
            return Web.get(BASE_URL + ROOMS + "/" + roomId + MESSAGES + "/before/" + messageId + "?amount=" + amount);
        }

        public static JSONArray getHistoryAfter(String roomId, String messageId) throws IOException {
            return Web.get(BASE_URL + ROOMS + "/" + roomId + MESSAGES + "/after/" + messageId);
        }

        public static void sendMessage(String roomId, String UUID, String username, String content) throws IOException {
            JSONObject obj = new JSONObject();
            obj.put("sentBy", username);
            obj.put("body", content);
            obj.put("sentByID", UUID);
            Date now = new Date();
            obj.put("sentOn", now.getTime());

            Web.post(BASE_URL + ROOMS + "/" + roomId + MESSAGES, obj.toString());
        }
    }

    public static class Rooms {
        public static JSONObject postCreateRoom(String name, Boolean isListed) throws IOException {
            JSONObject obj = new JSONObject();
            obj.put("name", name);
            return Web.post(BASE_URL + ROOMS + "?listed=" + isListed, obj.toString());
        }

        public static JSONArray getAllRooms() throws IOException {
            return Web.get(BASE_URL + ROOMS);
        }
    }

}
