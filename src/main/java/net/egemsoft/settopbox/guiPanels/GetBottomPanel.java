package net.egemsoft.settopbox.guiPanels;

import net.egemsoft.settopbox.ui.MainApplication;
import net.egemsoft.settopbox.utilities.MyJTextPane;
import net.egemsoft.settopbox.utilities.MyLogger;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by enbiya on 05.04.2016.
 */
public class GetBottomPanel extends JPanel {

    // arayüzün bottom kısmı

    private String MLDebugMode = "";
    private boolean SET_OPAQ = MainApplication.ALL_OPAQ_SETTINGS;

    public GetBottomPanel(String MLDebugMode){

        this.MLDebugMode = MLDebugMode;
        setOpaque(SET_OPAQ);
        setBorder(BorderFactory.createLineBorder(Color.white));
        setLayout(new MigLayout(MLDebugMode + ", gapy 0"));




//        JList logScreen = new JList( MyLogger.logScreenData );//my data
//        logScreen.setEnabled(false);
//
//        Font font = new Font(logScreen.getFont().getName(),Font.BOLD, logScreen.getFont().getSize());
//        logScreen.setFont(font);
//        logScreen.setForeground(Color.white);
//
//        JScrollPane pane = new JScrollPane(logScreen);
//
//        add(pane,"width 100%, height 100%");



        final MyJTextPane logScreen = new MyJTextPane(false);// word wrap false
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
            }
        );

        add(pane, "width 100%, height 100%");

    }

}
