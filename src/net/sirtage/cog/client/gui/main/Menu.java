package net.sirtage.cog.client.gui.main;

import javax.swing.*;

public class Menu extends JFrame{

    public Menu() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("COG");
        setSize(1200, 800);
        setResizable(false);
        setLayout(null);

        add(new InsertField());
        add(new RenderField());

    }
}
