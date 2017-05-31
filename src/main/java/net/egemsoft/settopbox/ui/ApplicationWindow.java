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
public class ApplicationWindow {

    private JFrame mainJFrame;

    private int MAIN_WINDOW_HEIGHT = 500;
    private int MAIN_WINDOW_WIDTH = 400;
    private String MLDebugMode = ""; // miglayout için parametre: debug




    public ApplicationWindow(){

        mainJFrame = new JFrame("Modem Arıza Tespit Platformu - Demo");

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            mainJFrame.setContentPane(new JLabel(new ImageIcon(new ClassPathResource("/images/background.jpg").getURL())));
            mainJFrame.setIconImage(new ImageIcon(new ClassPathResource("/images/favicon.png").getURL() ).getImage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainJFrame.setMaximumSize(new Dimension(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT));
        mainJFrame.setMinimumSize(new Dimension(MAIN_WINDOW_WIDTH-1, MAIN_WINDOW_HEIGHT-1));
        mainJFrame.setResizable(true);
        mainJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        int windowHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int windowWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        mainJFrame.setLocation((windowWidth - mainJFrame.getWidth())/2, (windowHeight - mainJFrame.getHeight())/2);// ekranı ortala

        mainJFrame.setLayout(new MigLayout(MLDebugMode));
        mainJFrame.getContentPane().add(new GetTopPanel(MLDebugMode),"width 100%, height 100, cell 0 0, grow");
        mainJFrame.getContentPane().add(new GetCenterPanel(MLDebugMode),"width 100%, cell 0 1, grow");
        mainJFrame.getContentPane().add(new GetBottomPanel(MLDebugMode), "width 100%, height 200, cell 0 2, grow");

        mainJFrame.setVisible(true);
        mainJFrame.pack();

        }


    }









//////////////////////// çöp kodlar

//    private JPanel test1Panel;
//    private JCheckBox cboxTest1;
//    private JLabel lblTest1;
//    private JButton btnTest1;

//    private JPanel getTest1(){// test aşamaları manuel
//
//        //miglayout özellikleri
////        MigLayout layout = new MigLayout("debug");
//        //new MigLayout("debug");--> component etrafını işaretliyor
//        //panel.add(comp2, "cell 1 0 2 1"); // "cell column row width height"
//        //panel.add(comp2, "gapright 10, height 30, width 250, cell 0 0"); //gapright:sağ boşluk, gapleft:sol boşluk, height-width:comp yük.en.
//
//        if(test1Panel == null){
//
//            test1Panel = new JPanel(new MigLayout(""));
//            test1Panel.setMaximumSize(new Dimension(getCenterPanel().getWidth(), 30));//büyük alan kaplamasın diye
//
//            cboxTest1 = new JCheckBox("birinci test");
//            test1Panel.add(cboxTest1, "gapright 10, height 30, width 250, cell 0 0");
//
//            lblTest1 = new JLabel();
//            lblTest1.setBackground(Color.RED);
//            lblTest1.setOpaque(true);
//            test1Panel.add(lblTest1, "gapright 10, height 30, width 30, cell 1 0");
//
//            btnTest1 = new JButton("try again");
//            test1Panel.add(btnTest1,"height 30, width 80, cell 2 0" );
//        }
//
//        return test1Panel;
//    }