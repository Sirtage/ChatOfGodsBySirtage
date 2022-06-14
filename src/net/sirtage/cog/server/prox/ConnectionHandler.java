package net.sirtage.cog.server.prox;

import net.sirtage.cog.App;
import net.sirtage.cog.Side;
import net.sirtage.cog.client.Params;
import net.sirtage.cog.server.User;
import net.sirtage.cog.server.processor.Processor;
import net.sirtage.cog.server.processor.Sender;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler{

    public final Socket socket;

    public final BufferedReader in;
    public final BufferedWriter out;

    public User userIn;

    private static int count;
    private final int id;
    private final Thread thread;

    private boolean finalised = false;

    public ConnectionHandler(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.id = ConnectionHandler.count();
        this.thread = new Thread(this::run);
    }
    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.id = ConnectionHandler.count();
        this.thread = new Thread(this::run);
    }

    public boolean isFinalised() {
        return finalised;
    }

    public void finalise() {
        this.finalised = true;
        this.thread.start();
    }

    public ConnectionHandler user(User user) {
        this.userIn = user;
        return this;
    }

    public boolean close() {
        try {
            socket.close();
            thread.stop();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void run() {
        if (App.SIDE==Side.CLIENT) {
            while (true) {
                if (finalised) {
                    try {
                        String input = in.readLine();
                        Processor.process(Sender.SERVER, input);
                    } catch (IOException e) {
                        close();
                        System.out.println("Disconnected. ");
                    }
                }
            }
        } else if (App.SIDE==Side.SERVER) {
            while (true) {
                if (finalised) {
                    String input = null;
                    try {
                        input = in.readLine();
                    } catch (IOException e) {
                        Server.SERVER.HANDLER.disconnect(this);
                        return;
                    }
                    Processor.process(new Sender(this.userIn.name, this), input);
                }
            }
        }
    }

    public static int count() {
        count++;
        return count-1;
    }

    public int getId() {
        return id;
    }
}
