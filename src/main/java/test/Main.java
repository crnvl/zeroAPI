package test;

import api.HugoChat;

import java.io.IOException;

public class Main {

    public static HugoChat chat;
    public static void main(String[] args) throws IOException, InterruptedException {
        chat = new HugoChat("API DEMO [BOT]");
        chat.login();

        chat.changeUsername("API DEMO v2 [BOT]");

        chat.sendActive();

        System.out.println(chat.getUsers());

        System.out.println(chat.getMessageHistory(1));

        chat.sendMessage("test sent by API DEMO");

        chat.createRoom("shit on the ground Room", true);
        System.out.println(chat.createRoom("haha room go brr", true));

        System.out.println(chat.getRooms());
    }

}
