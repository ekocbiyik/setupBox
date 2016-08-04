package settopbox.ui;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class BackgroundImageJFrame extends JFrame
{
JButton b1;
JLabel l1;
    public BackgroundImageJFrame() throws IOException {
    setTitle("Background Color for JFrame");
    setSize(400,400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

// Another way
    setLayout(new BorderLayout());
    setContentPane(new JLabel(new ImageIcon(new ClassPathResource("/images/background.jpg").getURL())));
    setLayout(new FlowLayout());
    l1=new JLabel("Here is a button");
    b1=new JButton("I am a button");
    add(l1);
    add(b1);
    // Just for refresh :) Not optional!
    setSize(399,399);
    setSize(400,400);
    }
    public static void main(String args[]) throws IOException {
    new BackgroundImageJFrame();
    }
} 