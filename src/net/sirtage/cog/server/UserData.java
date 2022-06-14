package net.sirtage.cog.server;

import jdk.nashorn.internal.parser.JSONParser;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {

    public static void start() {
        File file = new File("users.udat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<User> get() {
        File file = new File("users.udat");
        List<User> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] arp = reader.lines().toArray(String[]::new);
            for (String a: arp) {
                char[] k = a.toCharArray();
                StringBuilder temp = new StringBuilder();
                String name = null;
                String value = null;
                for (int i = 0; i < k.length; i++) {
                    if (k[i]=='@') {
                        name = temp.toString();
                        temp = new StringBuilder();
                    } else {
                        temp.append(k[i]);
                    }
                }
                value = temp.toString();
                if (value != null && name !=null) {
                    try {
                        users.add(new User(name, value));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
