package test;

import api.HugoChat;
import test.listeners.CheckForNew;

import java.io.IOException;

public class Main {

    public static String PREFIX = "?";

    public static HugoChat client;
    public static void main(String[] args) throws IOException, InterruptedException {
        client = new HugoChat("Z.E.R.O");
        client.login();
        client.setRoom("a161a501-793c-1379-8179-3e74c48600be");

        CheckForNew.eventListener(true, client);
    }

}
