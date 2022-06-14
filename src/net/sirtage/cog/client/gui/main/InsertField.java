package net.sirtage.cog.client.gui.main;

import net.sirtage.cog.MFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertField extends JPanel{

    public static Field field = new Field();
    public static Button button = new Button();

    private static class Field extends JTextField {
        public Field() {
            super();
            Dimension dimension = new Dimension();
            dimension.setSize(800, 40);
            setHorizontalAlignment(JLabel.LEFT);
            Font font = new Font("Sans", Font.PLAIN, 30);
            setFont(font);
            setPreferredSize(dimension);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    send(field.getText());
                }
            });
        }
    }
    private static class Button extends JButton {
        public Button() {
            super();
            Dimension dimension = new Dimension();
            dimension.setSize(200, 40);
            setPreferredSize(dimension);
            setText("Send");
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    send(field.getText());
                }
            });
        }
    }

    public InsertField() {
        super();
        setBackground(Color.RED);
        setBounds(0, 600, 1200, 100);
        add(field);
        add(button);
    }

    private static void send(String message) {
        RenderField.field.write(MFormat.format(null, message));
    }
}
