package net.egemsoft.settopbox.utilities;

import org.apache.log4j.Logger;

import javax.swing.text.*;
import java.awt.*;

/**
 * Created by enbiya on 12.04.2016.
 */
public class MyLogger {

    // bu class'ı oluşturma nedenimiz
    // loglar hem text dosyasına yazılsın hemde ekrandaki listbox içinde de gözüksün

//    public static DefaultListModel logScreenData = new DefaultListModel();
    private static Logger myLogger;// = Logger.getLogger(GetCenterPanel.class);

    public static StyledDocument textPaneLogData = new DefaultStyledDocument();
    private Style _styleError;
    private Style _styleSuccess;


    public MyLogger(Class clazz) {

        myLogger = Logger.getLogger(clazz);

    }

    public void error(Object message) {
//        logScreenData.addElement(message);
        myLogger.error(message);
        try {

            _styleError = textPaneLogData.addStyle("red", null);
            StyleConstants.setForeground(_styleError, Color.RED);
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", _styleError);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void success(Object message) {
//        logScreenData.addElement(message);
        myLogger.info(message);
        try {
            _styleSuccess = textPaneLogData.addStyle("green", null);
            StyleConstants.setForeground(_styleSuccess, Color.GREEN);
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", _styleSuccess);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void info(Object message) {
//        logScreenData.addElement(message);
        myLogger.info(message);
        try {
            textPaneLogData.insertString(textPaneLogData.getLength(), message.toString()+"\n", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void debug(Object message) {
//        logScreenData.addElement(message);
        myLogger.debug(message);
    }

    public boolean isDebugEnabled(){
        return myLogger.isDebugEnabled();
    }


}
