package api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
public class HugoChat {

    private String accountName;
    private String defaultRoomId = "00000000-0000-0000-0000-000000000000";
    private String UUID;
    private boolean reconnect = false;

    public HugoChat(String username) {
        this.accountName = username;
    }

    public void setRoom(String roomId) {
        this.defaultRoomId = roomId;
    }

    public void login() throws IOException {
        System.out.println("[INFO] Attempting login...");
        JSONObject res = Request.Users.postLogin(accountName);
        UUID = res.get("id").toString();
        System.out.println("[INFO] Login successful!");
        System.out.println("[USER] name: " + res.get("name") + ", id: " + UUID);
    }

    public void reconnect() throws IOException {
        System.out.println("[INFO] Reconnecting...");
        JSONObject res = Request.Users.postLogin(accountName);
        UUID = res.get("id").toString();
        System.out.println("[INFO] Reconnected!");
    }

    public void changeUsername(String username) throws IOException, InterruptedException {
        accountName = username;
        Request.Users.putChangeUsername(username, UUID);
    }

    public JSONArray getUsers() throws IOException {
        return Request.Users.getUsers(defaultRoomId);
    }

    public void sendActive() throws IOException {
        Request.Users.patchActive(defaultRoomId, UUID);
    }

    public JSONArray getMessageHistory(int amount) throws IOException {
        return Request.Messages.getHistory(defaultRoomId, amount);
    }

    public JSONArray getMessageHistoryBefore(String messageId, int amount) throws IOException {
        return Request.Messages.getHistoryBefore(defaultRoomId, messageId, amount);
    }

    public JSONArray getMessageHistoryAfter(String messageId) throws IOException {
        return Request.Messages.getHistoryAfter(defaultRoomId, messageId);
    }

    public void sendMessage(String content) throws IOException {
        Request.Messages.sendMessage(defaultRoomId, UUID, accountName, content);
    }

    public JSONObject createRoom(String name, Boolean isListed) throws IOException {
        return Request.Rooms.postCreateRoom(name, isListed);
    }

    public JSONArray getRooms() throws IOException {
        return Request.Rooms.getAllRooms();
    }
}
