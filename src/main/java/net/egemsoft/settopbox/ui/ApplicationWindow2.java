package net.egemsoft.settopbox.ui;

import net.egemsoft.settopbox.guiPanels.GetBottomPanel;
import net.egemsoft.settopbox.guiPanels.GetCenterPanel;
import net.egemsoft.settopbox.guiPanels.GetTopPanel;
import net.miginfocom.swing.MigLayout;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by enbiya on 21.03.2016.
 */
public class ApplicationWindow2 {

    private JFrame mainJFrame;

    private int MAIN_WINDOW_HEIGHT = 500;
    private int MAIN_WINDOW_WIDTH = 400;
    private String MLDebugMode = "debug"; // miglayout için parametre: debug




    public ApplicationWindow2(){

        mainJFrame = new JFrame("Kiosk Test");
//        mainJFrame.setUndecorated(true); bu kısımtransparent için
//        AWTUtilities.setWindowOpacity(mainJFrame, 0.6f);

        try {
            mainJFrame.setContentPane(new JLabel(new ImageIcon(new ClassPathResource("/images/background.jpg").getURL())));
            mainJFrame.setIconImage(new ImageIcon(new ClassPathResource("/images/favicon.png").getURL() ).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        mainJFrame.setMaximumSize(new Dimension(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT));
        mainJFrame.setMinimumSize(new Dimension(MAIN_WINDOW_WIDTH-1, MAIN_WINDOW_HEIGHT-1));
        mainJFrame.setResizable(false);
        mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int windowHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        mainJFrame.setLocation((windowWidth - mainJFrame.getWidth())/2, (windowHeight - mainJFrame.getHeight())/2);// ekranı ortala

        mainJFrame.setLayout(new MigLayout(MLDebugMode));


        mainJFrame.getContentPane().add(new GetTopPanel(MLDebugMode),"width 100%, height 100, cell 0 0, grow");
        mainJFrame.getContentPane().add(new GetCenterPanel(MLDebugMode),"cell 0 1, grow");
        mainJFrame.getContentPane().add(new GetBottomPanel(MLDebugMode), "width 100%, height 200, cell 0 2, grow");

        mainJFrame.setVisible(true);
        mainJFrame.pack();

    }


}
