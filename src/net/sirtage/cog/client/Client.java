package net.sirtage.cog.client;

import net.sirtage.cog.server.User;
import net.sirtage.cog.server.processor.Processor;
import net.sirtage.cog.server.prox.ConnectionHandler;
import net.sirtage.cog.server.console.Console;
import net.sirtage.cog.server.prox.Server;

import java.io.IOException;
import java.util.Scanner;

public class Client {

    public static ConnectionHandler handler;

    public static Console console;

    public static void sendToServer(String cf) {
        try {
            Processor.send(Client.handler, cf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ip: ");
        String ip = scanner.nextLine();
        System.out.print("port: ");
        int port = scanner.nextInt();
        try {
            handler = new ConnectionHandler(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
            handler.close();
            return 1;
        }

        System.out.print("login: ");
        String login = scanner.next();
        try {
            Processor.send(handler, login);
            String answer1 = handler.in.readLine();
            if (answer1.equalsIgnoreCase("1")) {
                System.out.print("password: ");
                String password = scanner.next();
                Processor.send(handler, password);
                String answer2 = handler.in.readLine();
                if (answer2.equalsIgnoreCase("1")) {
                    System.out.println("Logged as " + login + ".");
                    handler.finalise();
                } else {
                    System.out.println("Wrong password.");
                    handler.close();
                    return 1;
                }
            } else {
                System.out.println("User doesn't exist.");
                handler.close();
                return 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            handler.close();
            return 1;
        }

        console = new Console();

        return 2;
    }
}
