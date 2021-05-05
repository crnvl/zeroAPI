package api;

import org.json.JSONObject;
import utils.Web;

import java.io.IOException;

public class Request {

    private static String BASE_URL = "http://161.97.165.1:8080/api/v2";
    private static String ROOMS = "/rooms";
    private static String USERS = "/users";
    private static String MESSAGES = "/messages";

    public class Users {
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

        public static JSONObject getUsers(String roomId) throws IOException {
            return Web.get(BASE_URL + ROOMS + "/" + roomId + USERS);
        }

        public static void patchActive(String roomId, String UUID) throws IOException {
            Web.patch(BASE_URL + ROOMS + "/" + roomId + USERS + "/active/" + UUID, "");
        }
    }

}
