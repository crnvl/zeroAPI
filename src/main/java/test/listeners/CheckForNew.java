package test.listeners;

import api.HugoChat;
import org.json.JSONArray;
import org.json.JSONObject;
import test.Main;
import utils.Misc;

import java.io.IOException;
import java.util.Collections;

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
                        if(arguments[0].startsWith("?")) {
                            client.sendMessage("Invalid Content");
                        } else {
                            if(arguments.length > 1) {
                                String uwufy = latest.replace(arguments[0], "");
                                client.sendMessage(Misc.uwu(uwufy));
                            }else {
                                String uwufy = new JSONObject(client.getMessageHistory(2).get(0).toString()).get("body").toString();
                                client.sendMessage(Misc.uwu(uwufy));
                            }
                        }
                    }
                    case "room" -> {
                        if(arguments.length > 1) {
                            JSONArray rooms = new JSONArray(client.getRooms().toString());

                            String roomId = "";
                            for (int i = 0; i < rooms.length(); i++) {
                                JSONObject room = new JSONObject(rooms.get(i).toString());
                                if(room.get("name").toString().contains(arguments[1]))
                                    roomId = room.get("id").toString();
                            }

                            if(!roomId.isEmpty()) {

                                client.sendMessage("Changing room...");
                                client.setRoom(roomId);
                                client.sendMessage("Successfully changed room!");
                            }else {
                                client.sendMessage("Failed to change room!");
                            }

                        }else
                            client.sendMessage("Please specify another argument!");
                    }
                    case "help" -> {
                        client.sendMessage(
                                "__**COMMAND HELP**__\\\n" +
                                        "`" + Main.PREFIX + "ping` | Test command\\\n" +
                                        "`" + Main.PREFIX + "stop` | Stop the Bot\\\n" +
                                        "`" + Main.PREFIX + "tom`  | tom\\\n" +
                                        "`" + Main.PREFIX + "nick <nickname>` | Set the Bot's Nickname\\\n" +
                                        "`" + Main.PREFIX + "uwufy (text)` | Text uwufier\\\n" +
                                        "`" + Main.PREFIX + "room <room>` | Change the bot's room\\\n"
                        );
                    }
                }
            }
        }
    }

}
