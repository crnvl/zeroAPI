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

        CheckForNew.eventListener(true, client);
    }

}
