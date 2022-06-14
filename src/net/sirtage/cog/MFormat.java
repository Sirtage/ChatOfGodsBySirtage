package net.sirtage.cog;

import net.sirtage.cog.App;
import net.sirtage.cog.server.processor.Sender;

import java.util.List;

public class MFormat {
    public static String format(Sender sender, String message) {
        return sender.USERNAME+">"+message;
    }

    public static String messageEncode(String message, List<String> params) {
        String f = "";
        for (String s: params) {
            f+="$"+s;
        }
        f+=":"+message;
        return f;
    }
}
