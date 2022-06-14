package net.sirtage.cog.server.console;

import net.sirtage.cog.App;
import net.sirtage.cog.MFormat;
import net.sirtage.cog.client.Client;
import net.sirtage.cog.client.Params;
import net.sirtage.cog.server.User;
import net.sirtage.cog.server.prox.ConnectionHandler;
import net.sirtage.cog.server.prox.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Console {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private String lastRec;

    public Console() {
        new Thread(() -> {
            try {
                while (true) {
                    this.lastRec = reader.readLine();
                    process(this.lastRec);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }

    public static void process(String command) {
        switch (App.SIDE) {
            case SERVER:
                String[] cmd_form = command.split(" ");
                switch (cmd_form[0]) {
                    default:
                        System.out.println("Wrong command");
                        break;
                    case "exit":
                        App.stop();
                        break;
                    case "list":
                        int i = 0;
                        for (ConnectionHandler handler: Server.SERVER.HANDLER.getCons()) {
                            System.out.println(i + ". | " + handler.userIn.name + " | isAlive: "+!handler.socket.isClosed());
                            i++;
                        }
                        break;
                    case "kick":

                        break;
                    case "u":
                        if (cmd_form.length>1) {
                            switch (cmd_form[1]) {
                                default:
                                    System.out.println("Wrong command argument");
                                    break;
                                case "list":
                                    System.out.print("User list: [");
                                    for (User user : Server.SERVER.users) {
                                        System.out.print(user.name + ";");
                                    }
                                    System.out.print("]\n");
                                    break;
                                case "update":
                                    Server.SERVER.update();
                                    break;
                            }
                        } else {
                            System.out.println("Command for interacting with user list.");
                        }
                        break;
                }
                break;
            case CLIENT:
                if (command.toCharArray()[0]!='/') {
                    Client.sendToServer(MFormat.messageEncode(command, Params.MESSAGE_PROP));
                } else {
                    System.out.println("CLIENT");
                }
                break;
        }
    }

    public void send(String message) {
        System.out.println(message);
    }

    public String getLastReceive() {
        return this.lastRec;
    }
}
