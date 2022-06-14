package net.sirtage.cog.client.prox;

public class HName {

    private final String ip;
    private final int port;
    private final String password;

    public HName(String ip, int port, String password) {
        this.ip = ip;
        this.port = port;
        this.password = password;
    }

    public static HName decode(String hostname) {
        String ip = null;
        int port = 0;
        String password = null;
        char[] arr = hostname.toCharArray();
        String temp = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==':') {
                ip=temp;
                continue;
            } else if (arr[i]=='$') {
                port=Integer.parseInt(temp);
                continue;
            }
            temp+=arr[i];
        }
        password = temp;
        return new HName(ip, port, password);
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }
}
