package net.sirtage.cog.server.processor;

import net.sirtage.cog.App;
import net.sirtage.cog.MFormat;
import net.sirtage.cog.Side;
import net.sirtage.cog.client.Params;
import net.sirtage.cog.server.prox.ConnectionHandler;
import net.sirtage.cog.server.prox.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Processor {
    public static boolean process(Sender sender, String message) {
        StringBuilder temp = new StringBuilder();
        String content = null;
        List<String> params = new ArrayList<>();

        char[] pr = message.toCharArray();
        char last = ' ';
        for (int i = 0; i < pr.length; i++) {
            if (pr[i] == ':' || pr[i] == '$') {

                if (last == '$') {
                    params.add(temp.toString());
                }

                if (pr[i] == ':') {
                    last = ':';
                } else if (pr[i] == '$') {
                    last = '$';
                }
                temp = new StringBuilder();
            } else {
                temp.append(pr[i]);
            }
        }
        content=temp.toString();

        if (App.SIDE == Side.SERVER) {
            if (params.contains("msg")) {
                try {
                    sendToAll(sender, content, Params.MESSAGE_PROP);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (App.SIDE == Side.CLIENT) {
            if (params.contains("msg")) {
                System.out.println(content);
            } if (params.contains("info")) {
                System.out.println("[INFO] "+content);
            }
        }

        return true;
    }

    public static void sendToAll(Sender sender, String message, List<String> params) throws IOException {
        String tempo = message;
        if (params.contains("msg")) {
            tempo = MFormat.format(sender, message);
        }
        if (App.SIDE == Side.SERVER) {
            for (ConnectionHandler handler : Server.SERVER.HANDLER.getCons()) {
                try {
                    send(handler, MFormat.messageEncode(tempo, params));
                } catch (IOException e) {
                    Server.SERVER.HANDLER.disconnect(handler);
                }
            }
            System.out.println(tempo);
        }
    }

    public static void send(ConnectionHandler handler, String message) throws IOException {
        handler.out.write(message + "\n");
        handler.out.flush();
    }
}
