package net.sirtage.cog.server.processor;

import net.sirtage.cog.server.prox.ConnectionHandler;

public class Sender {

    public static Sender SERVER = new Sender("Server", null);

    public final String USERNAME;
    public final ConnectionHandler handler;

    public Sender(String username, ConnectionHandler handler) {
        this.USERNAME = username;
        this.handler = handler;
    }
}
