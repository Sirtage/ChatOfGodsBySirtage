package net.sirtage.cog.server.prox;

import net.sirtage.cog.client.Params;
import net.sirtage.cog.server.User;
import net.sirtage.cog.server.console.Console;
import net.sirtage.cog.server.processor.Processor;
import net.sirtage.cog.server.processor.Sender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerHandler {

    private final List<ConnectionHandler> cons = new ArrayList<>();
    public Console console;
    public final ServerSocket serverSocket;

    public Thread updatingThread;

    public ConnectionHandler addConnection(Socket socket) throws IOException {
        System.out.println("trying to add "+socket.getInetAddress()+":"+socket.getLocalPort());
        ConnectionHandler connection = new ConnectionHandler(socket);
        String login = connection.in.readLine();
        System.out.println("Attempting to join "+connection+" as "+login+".");
        if (Server.SERVER.isUserExist(login)) {
            Processor.send(connection, "1");
            String password = connection.in.readLine();
            User user = Server.SERVER.getUser(login, password);
            if (user!=null) {
                Processor.send(connection, "1");
                connection.user(user);
                cons.add(connection);
                connection.finalise();
                Processor.sendToAll(new Sender(connection.userIn.name, connection),
                        connection.userIn.name+" has joined.", Params.INFO_PROP);
                return connection;
            } else {
                Processor.send(connection, "0");
                System.out.println("Disconnected "+connection+" due to wrong password.");
                connection.close();
            }
        } else {
            Processor.send(connection, "0");
            System.out.println("Disconnected "+connection+" due to wrong login.");
            connection.close();
        }
        return null;
    }

    public void disconnect(ConnectionHandler handler) {
        handler.close();
        cons.remove(handler);
        try {
            Processor.sendToAll(Sender.SERVER, handler.userIn.name+" disconnected.", Params.INFO_PROP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerHandler(int port, boolean hasConsole) throws IOException {
        this.serverSocket = new ServerSocket(port);
        if (hasConsole) {
            this.console = new Console();
        }
        this.updatingThread = new Thread(this::update);
        this.updatingThread.start();
    }

    public List<ConnectionHandler> getCons() {
        return cons;
    }

    public void update() {
        while (true) {
            try {
                for (ConnectionHandler con : cons) {
                    if (con.socket.isClosed()) {
                        disconnect(con);
                    }
                }
            } catch (Exception e) {
                //TODO
            }
        }
    }
}
