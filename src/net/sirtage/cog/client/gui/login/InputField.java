package net.sirtage.cog.client.gui.login;

import javax.swing.*;
import java.awt.*;

public class InputField extends JPanel {
    
    public static class Form {
        public static final int WIDTH = 450;
    }

    public static Field0 ipp = new Field0();
    public static Field1 login = new Field1();
    public static Field2 password = new Field2();

    public static class Field0 extends JPanel {

        public Label label = new Label();
        public Comp comp = new Comp();

        public static class Label extends JLabel {
            public Label() {
                super("IP$password: ");
                Dimension dimension = new Dimension();
                dimension.setSize(100, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }
        public static class Comp extends JTextField {
            public Comp() {
                super();
                Dimension dimension = new Dimension();
                dimension.setSize(InputField.Form.WIDTH, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }

        public Field0() {
            super();
            setBackground(Color.RED);
            setBounds(0, 0, InputField.Form.WIDTH, 100);

            add(label);
            add(comp);
        }
    }

    public static class Field1 extends JPanel {

        public Label label = new Label();
        public Comp comp = new Comp();

        public static class Label extends JLabel {
            public Label() {
                super("Login: ");
                Dimension dimension = new Dimension();
                dimension.setSize(100, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }
        public static class Comp extends JTextField {
            public Comp() {
                super();
                Dimension dimension = new Dimension();
                dimension.setSize(InputField.Form.WIDTH, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }

        public Field1() {
            super();
            setBackground(Color.RED);
            setBounds(0, 200, InputField.Form.WIDTH, 100);

            add(label);
            add(comp);
        }
    }

    public static class Field2 extends JPanel {

        public Label label = new Label();
        public Comp comp = new Comp();

        public static class Label extends JLabel {
            public Label() {
                super("Password: ");
                Dimension dimension = new Dimension();
                dimension.setSize(100, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }
        public static class Comp extends JTextField {
            public Comp() {
                super();
                Dimension dimension = new Dimension();
                dimension.setSize(InputField.Form.WIDTH, 40);
                setHorizontalAlignment(JLabel.LEFT);
                Font font = new Font("Sans", Font.PLAIN, 30);
                setFont(font);
                setPreferredSize(dimension);
            }
        }

        public Field2() {
            super();
            setBackground(Color.RED);
            setBounds(0, 400, InputField.Form.WIDTH, 100);

            add(label);
            add(comp);
        }
    }






    public InputField() {
        super();
        setBackground(Color.PINK);
        setBounds(0, 0, 700, 400);
        add(ipp);
        add(login);
        add(password);
    }
}
