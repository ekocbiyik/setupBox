package net.egemsoft.settopbox.guiPanels;

import net.egemsoft.settopbox.ui.MainApplication;
import net.miginfocom.swing.MigLayout;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by enbiya on 05.04.2016.
 */
public class GetTopPanel extends JPanel {

    // bu panel arayüzdeki top kısmı

    private String MLDebugMode = "";
    private boolean SET_OPAQ = MainApplication.ALL_OPAQ_SETTINGS;

    public GetTopPanel(String MLDebugMode){

        this.MLDebugMode = MLDebugMode;
        setOpaque(SET_OPAQ);
        setBorder(BorderFactory.createLineBorder(Color.white));
        setLayout(new MigLayout(MLDebugMode + ", gapy 0"));

        JLabel imageLabel = new JLabel();
        imageLabel.setOpaque(SET_OPAQ);

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(new ClassPathResource("/images/logo1.png").getURL());
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel.setIcon(icon);
        add(imageLabel, "span 2 2, height 90, width 300");


        String  sText  = "<html><h3><b>Egemsoft-Merkez</b></h3>  <p> Version: v2.6.1</p>  </html>";
        JLabel bayiLabel = new JLabel(sText, SwingConstants.LEFT);
        bayiLabel.setOpaque(SET_OPAQ);
        add(bayiLabel, "width 100");

    }


}
