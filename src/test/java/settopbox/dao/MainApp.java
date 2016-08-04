package settopbox.dao;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Created by enbiya on 15.04.2016.
 */
public class MainApp {


    static  MyLogger logger = new MyLogger(MainApp.class);

    public static void main(String[] args) {


        JFrame frame = new JFrame("Log Screen");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLocation(600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton error = new JButton("Error");
        error.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    logger.error(i+" error..");
                }
            }
        });


        JButton debug = new JButton("Debug");
        debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = "";
                for (int i = 0; i < 10; i++) {
                    a += " debug..";
                }
                logger.debug(a);
            }
        });


        JButton info = new JButton("Info");
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    logger.info(i+" info..");
                }
            }
        });


        final MyJTextPane logScreen = new MyJTextPane(false);// uzun satırları kwrap yapma
        logScreen.setEditable(false);
        logScreen.setStyledDocument(MyLogger.textPaneLogData);

        Font font = new Font(logScreen.getFont().getName(), Font.PLAIN, logScreen.getFont().getSize());
        logScreen.setFont(font);
        logScreen.setForeground(Color.black);
        logScreen.setBackground(Color.white);


        JScrollPane pane = new JScrollPane(logScreen);

        pane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                  public void adjustmentValueChanged(AdjustmentEvent e){
                      logScreen.select(logScreen.getCaretPosition()*logScreen.getFont().getSize() ,0);
                  }
         });

        frame.add(error);
        frame.add(debug);
        frame.add(info);
        frame.add(pane);


        frame.setVisible(true);
        frame.pack();

    }

}
