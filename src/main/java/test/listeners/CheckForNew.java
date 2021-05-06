package test.listeners;

import api.HugoChat;
import org.json.JSONObject;
import test.Main;

import java.io.IOException;

public class CheckForNew {

    public static void eventListener(boolean run, HugoChat client) throws IOException, InterruptedException {
        while (run) {
            client.sendActive();

            String latest = new JSONObject(client.getMessageHistory(1).get(0).toString()).get("body").toString();

            if (latest.startsWith(Main.PREFIX)) {
                String[] arguments = latest.split(" ");

                switch (arguments[0].replace(Main.PREFIX, "")) {
                    case "ping" -> {
                        client.sendMessage(
                                "Pong!"
                        );
                    }
                    case "stop" -> {
                        client.sendMessage(
                                "Shutting down..."
                        );
                        run = false;
                        System.exit(0);
                    }
                    case "tom" -> {
                        client.sendMessage(
                                "![Tom](https://cdn.discordapp.com/emojis/811324632082415626.png \"tom\")"
                        );
                    }
                    case "nick" -> {
                        if(arguments.length > 1) {
                            client.changeUsername(arguments[1]);
                            client.sendMessage("Changed Name to `" + arguments[1] + "`");
                        }else
                            client.sendMessage("Please specify another argument!");
                    }
                    case "uwufy" -> {
                        if(arguments.length > 1) {
                            String uwufy = new JSONObject(client.getMessageHistory(2).get(1)).get("body").toString();


                            client.sendMessage("Changed Name to `" + arguments[1] + "`");
                        }else {
                            client.sendMessage("Please specify another argument!");
                        }
                    }
                }
            }
        }
    }

}
