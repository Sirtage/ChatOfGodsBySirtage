package net.sirtage.cog.server.prox;

import net.sirtage.cog.server.User;
import net.sirtage.cog.server.UserData;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    public List<User> users = new ArrayList<>();

    public boolean isUserExist(String name) {
        for (User user: users) {
            if (user.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String name, String password) {
        for (User user: users) {
            if (user.name.equalsIgnoreCase(name) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static final int DEFAULT_PORT = 2090;
    public static Server SERVER;

    public final ServerHandler HANDLER;

    public static void init(int port, boolean consoled) throws IOException {
        SERVER = new Server(port, consoled);
        SERVER.start();
    }

    private Server(int port, boolean hasConsole) throws IOException {
        this.HANDLER = new ServerHandler(port, hasConsole);
        UserData.start();
        update();
    }

    @Override
    public void run() {
        System.out.println("Started server on port: " + HANDLER.serverSocket.getLocalPort());
        while(true) {
            try {
                Socket socket = HANDLER.serverSocket.accept();
                new Thread(() -> {
                    try {
                        HANDLER.addConnection(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket timeout!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void close() {
        try {
            SERVER.HANDLER.serverSocket.close();
            SERVER.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.users = UserData.get();
    }
}
