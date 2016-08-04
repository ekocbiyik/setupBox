package settopbox.ui;

import net.egemsoft.settopbox.enums.TestOrder;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by enbiya on 21.03.2016.
 */
public class ApplicationWindowTest {// extends JFrame {

    private JFrame mainJFrame;

    private int MAIN_WINDOW_HEIGHT = 500;
    private int MAIN_WINDOW_WIDTH = 400;
    private String MLDebugMode = ""; // miglayout için parametre: debug

    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;

    private JPanel selectAllTestPanel;
    private JCheckBox cboxSelectAll;
    private JButton btnStartTests;




    @Test
    public void init(){

        mainJFrame = new JFrame("SetTopBox");

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


        // componentleri ekle
        mainJFrame.getContentPane().add(getTopPanel(), "height 100, cell 0 0, grow");
        mainJFrame.getContentPane().add(getCenterPanel(), "cell 0 1, grow");
        mainJFrame.getContentPane().add(getBottomPanel(), "height 100, cell 0 2, grow");//grow: enine yayıyor push: düşeyde yayıyor

        mainJFrame.setVisible(true);
        mainJFrame.pack();

    }

    private JPanel getTopPanel(){

        if (topPanel == null){
            topPanel = new JPanel();
            topPanel.add(new JLabel("bu kısım top"));
        }
        return topPanel;
    }

    private JPanel getBottomPanel(){

        if(bottomPanel == null){
            bottomPanel = new JPanel();
            bottomPanel.add(new Label("burası bottom panel"));
        }
        return bottomPanel;
    }

    private JPanel getCenterPanel(){

        //test butonları burada
        if(centerPanel == null){

            centerPanel = new JPanel(new MigLayout(MLDebugMode+",gapy 0"));
            centerPanel.setBorder(BorderFactory.createLineBorder(Color.white));

            // TestOrder'daki enum ile döngü kurup testleri oluştur
            for (int i = 0; i < TestOrder.values().length; i++) {
                centerPanel.add(getMyJTestPanel(TestOrder.values()[i].name(), TestOrder.values()[i].getValue()), "wrap, grow");
            }
            centerPanel.add(getSelectAllTest(),"wrap, grow");
        }

        return centerPanel;
    }


    private JPanel getMyJTestPanel(String testName, String testContext){


        JPanel myTestPanel = new JPanel(new MigLayout(MLDebugMode+",insets 0"));
        myTestPanel.setName(testName);

        final JCheckBox cboxInfo = new JCheckBox(testContext);
        myTestPanel.add(cboxInfo, "gapright 10, height 30, width 250, cell 0 0");

        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(false);
        myTestPanel.add(bar,"height 10, width 100, cell 1 0");

        JLabel lblResult = new JLabel();
        lblResult.setBackground(Color.RED);
        lblResult.setOpaque(true);
        myTestPanel.add(lblResult, "gapright 10, height 25, width 25, cell 2 0");


        cboxInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(cboxInfo.getText() +": "+ cboxInfo.isSelected());// bu kısımlar logda basılsın // TODO: 25.03.2016 log ekle
                cboxSelectAll.setSelected(false);
            }
        });

        return myTestPanel;
    }

    private JPanel getSelectAllTest(){

        if(selectAllTestPanel == null){

            selectAllTestPanel = new JPanel(new MigLayout(MLDebugMode+", insets 0"));

            cboxSelectAll = new JCheckBox("Tüm Testleri Seç");
            selectAllTestPanel.add(cboxSelectAll, "gapright 15, height 30, width 250, cell 0 0");

            btnStartTests = new JButton("Testi Başlat");
            selectAllTestPanel.add(btnStartTests, "gapleft 0, height 30, width 130, cell 0 0");

            cboxSelectAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean isChecked = abstractButton.getModel().isSelected();

                    for (int i = 0; i < getCenterPanel().getComponents().length; i++) {

                        if (getCenterPanel().getComponents()[i] instanceof  JPanel){
                            JPanel panel = ((JPanel) getCenterPanel().getComponents()[i]);
                            ((JCheckBox) panel.getComponents()[0]).setSelected(isChecked);
                            System.out.println(((JCheckBox) panel.getComponents()[0]).getText()+": "+((JCheckBox) panel.getComponents()[0]).isSelected());// // TODO: 25.03.2016 log ekle
                        }
                    }
                }
            });


            btnStartTests.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    // seçili testleri bul o testlere ait panelin id'sini bir metoda gönder o metot ilgili testi başlatsın
                    // tekrarla butonunda ise hangi buton tıklanmışsa o butona ait panelin id'sini yukardaki metoda gönder
                    // sonuçlar static bir map'te tutulsun böylece yeniden başlatılan testin de sonuçlarıda kaydedilir


                }
            });
        }

        return selectAllTestPanel;
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