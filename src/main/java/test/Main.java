package test;

import api.HugoChat;

import java.io.IOException;

public class Main {

    public static HugoChat chat;
    public static void main(String[] args) throws IOException, InterruptedException {
        chat = new HugoChat("API DEMO [BOT]");
        chat.setRoom("a161a501-793c-1379-8179-3e74c48600be");
        chat.setPrefix("?");
        chat.login();

        chat.changeUsername("API DEMO v2 [BOT]");

        chat.sendActive();
    }

}
