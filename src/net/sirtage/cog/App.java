package net.sirtage.cog;

import net.sirtage.cog.client.Client;
import net.sirtage.cog.server.prox.Server;

import java.io.IOException;

public class App {

    public static Runtime runtime = Runtime.getRuntime();

    public static Side SIDE = Side.NON_STATED;

    //private static Menu menu = new Menu();
    //private static LoginScreen loginScreen = new LoginScreen();

    public static void stop() {
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("server")) {
                int port = Server.DEFAULT_PORT;
                if (args.length > 1) {
                    port = Integer.parseInt(args[1]);
                }
                try {
                    Server.init(port, true);
                    SIDE = Side.SERVER;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equalsIgnoreCase("client")) {
                SIDE = Side.CLIENT;
                if (args.length > 1) {
                    /*for (int i = 1; i < args.length; i++) {
                        if (args[i].equalsIgnoreCase("-l")) {
                            try {
                                i++;
                                HOSTNAME = args[i];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Hostname needed.");
                                return;
                            }
                        } else if (args[i].equalsIgnoreCase("-n")) {
                            try {
                                i++;
                                USERNAME = args[i];
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Username needed.");
                                return;
                            }
                        } else if (args[i].equalsIgnoreCase("-t")) {
                            USERNAME = "Test";
                            rt();
                            break;
                        }
                    }*/
                } else {
                    Client.run();
                }
            } else if (args[0].equalsIgnoreCase("test")) {
                Client.run();
            }
        } else {
            //loginScreen.setVisible(true);
        }
    }

    public static void rt() {

    }
}
