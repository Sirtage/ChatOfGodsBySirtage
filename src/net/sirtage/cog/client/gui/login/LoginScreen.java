package net.sirtage.cog.client.gui.login;

import javax.swing.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("COG");
        setSize(700, 400);
        setResizable(false);
        setLayout(null);

        add(new InputField());
    }
}
