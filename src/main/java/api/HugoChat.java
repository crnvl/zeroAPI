package api;

import org.json.JSONObject;

import java.io.IOException;
public class HugoChat {

    private String accountName;
    private String defaultRoomId = "00000000-0000-0000-0000-000000000000";
    private String UUID;

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

    public void changeUsername(String username) throws IOException, InterruptedException {
        Request.Users.putChangeUsername(username, UUID);
    }

    //pls fix
//    public JSONObject getUsers() throws IOException {
//        return Request.Users.getUsers(defaultRoomId);
//    }

    public void sendActive() throws IOException {
        Request.Users.patchActive(defaultRoomId, UUID);
    }

}
