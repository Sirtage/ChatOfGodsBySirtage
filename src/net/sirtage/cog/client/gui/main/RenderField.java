package net.sirtage.cog.client.gui.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderField extends JPanel {

    public static Field field = new Field();

    public static class Field extends JList<String> {

        private final List<String> cont = new ArrayList<>();

        public Field() {
            super();
            Dimension dimension = new Dimension();
            dimension.setSize(1000, 600);
            setPreferredSize(dimension);
        }

        public void write(String format) {
            cont.add(format);
            update();
        }

        public void update() {
            setListData(cont.toArray(new String[0]));
        }
    }

    public RenderField() {
        super();
        setBackground(Color.BLUE);
        setBounds(0, 0, 1000, 600);
        add(field);
    }
}
