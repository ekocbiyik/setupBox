package net.egemsoft.settopbox.guiPanels;

import net.egemsoft.settopbox.utilities.MyLogger;
import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestOrder;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.testsManagement.firmwareControlTestCore.FirmwareKontrolTesti;
import net.egemsoft.settopbox.testsManagement.internetBaglantiTestCore.InternetBaglantiTesti;
import net.egemsoft.settopbox.testsManagement.lanPortlariTestCore.LanPortlariTesti;
import net.egemsoft.settopbox.testsManagement.ledTestCore.LedTesti;
import net.egemsoft.settopbox.testsManagement.modemErisimTestCore.ModemErisimTesti;
import net.egemsoft.settopbox.testsManagement.modemLoginTestCore.ModemLoginTesti;
import net.egemsoft.settopbox.testsManagement.resetButonTestCore.ResetButonTesti;
import net.egemsoft.settopbox.testsManagement.wifiIslevsellikTestCore.WifiIslevsellikTesti;
import net.egemsoft.settopbox.ui.MainApplication;
import net.miginfocom.swing.MigLayout;
import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by enbiya on 05.04.2016.
 */
public class GetCenterPanel extends JPanel {

    final static MyLogger logger = new MyLogger(GetCenterPanel.class);

    private String MLDebugMode = "";

    private JPanel selectAllTestPanel;
    private JCheckBox cboxSelectAll;
    private JButton btnStartTests;
    private JButton btnEndTests;

    private ImageIcon fail;
    private ImageIcon success;

    private boolean SET_OPAQ = MainApplication.ALL_OPAQ_SETTINGS;


    private static Map<String, Object> testResultsContext;


    public GetCenterPanel(String MLDebugMode){

        this.MLDebugMode = MLDebugMode;
        setLayout(new MigLayout(MLDebugMode + ",gapy 0"));
        setBorder(BorderFactory.createLineBorder(Color.white));
        setOpaque(SET_OPAQ);///

        for (int i = 0; i < TestOrder.values().length; i++) {
            add(getMyJTestPanel(TestOrder.values()[i].name(), TestOrder.values()[i].getValue()), "wrap, grow");
        }

        add(getSelectAllTest(),"wrap, grow");
        testResultsContext = new LinkedHashMap<String, Object>();//

    }



    private JPanel getMyJTestPanel(String testName, String testContext){

        JPanel myTestPanel = new JPanel(new MigLayout(MLDebugMode+",insets 0"));
        myTestPanel.setName(testName);
        myTestPanel.setOpaque(SET_OPAQ);///

        final JCheckBox cboxInfo = new JCheckBox(testContext);
        cboxInfo.setName(testName);//!!! testler testResultContext içinde bu field'a göre tutuluyor
        cboxInfo.setOpaque(SET_OPAQ);
        Font font = new Font(cboxInfo.getFont().getName(),Font.BOLD, cboxInfo.getFont().getSize());
        cboxInfo.setFont(font);


        // testlerde enable olanların hepsi seçilmiş ise "tüm testleri seç" kutucuğu işaretlensin
        cboxInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean hepsiSecili = false;
                for (int i = 0; i < getComponents().length-1; i++) {

                    if ( getComponents()[i] instanceof  JPanel){
                        JPanel panel = ((JPanel) getComponents()[i]);
                        JCheckBox box = (JCheckBox) panel.getComponents()[0];

                        if (box.isEnabled() && box.isSelected()){
                            hepsiSecili = true;

                        }else{

                            // bir tane bile seçilmeyen varsa işlemi iptal et
                            if (box.isEnabled()) {
                                hepsiSecili = false;
                                break;
                            }

                        }
                    }
                }

                if (hepsiSecili){
                    cboxSelectAll.setSelected(true);
                }
            }
        });

        myTestPanel.add(cboxInfo, "gapright 10, height 30, width 200, cell 0 0");



        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(false);
        bar.setVisible(false);
        myTestPanel.add(bar,"height 10, width 150, cell 1 0");


        try {

            fail = new ImageIcon(new ClassPathResource("/images/fail.png").getURL());
            success = new ImageIcon(new ClassPathResource("/images/success.png").getURL());

        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel lblResult = new JLabel();
        lblResult.setOpaque(SET_OPAQ);
        lblResult.setVisible(false);
        myTestPanel.add(lblResult, "gapleft 10, height 25, width 25, cell 2 0");


        cboxInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logger.info(cboxInfo.getText() +": "+ cboxInfo.isSelected());
                cboxSelectAll.setSelected(false);
            }
        });

        return myTestPanel;
    }


    private JPanel getSelectAllTest(){

        if(selectAllTestPanel == null){

            selectAllTestPanel = new JPanel(new MigLayout(MLDebugMode+", insets 0"));
            selectAllTestPanel.setOpaque(SET_OPAQ);

            cboxSelectAll = new JCheckBox("Tüm Testleri Seç");
            cboxSelectAll.setOpaque(SET_OPAQ);
            Font font = new Font(cboxSelectAll.getFont().getName(),Font.BOLD, cboxSelectAll.getFont().getSize());
            cboxSelectAll.setFont(font);
            selectAllTestPanel.add(cboxSelectAll, "gapright 15, height 30, width 120, cell 0 0");

            btnEndTests = new JButton("Testi Bitir");
            btnEndTests.setEnabled(false);// başlangıçta false olmalı
            btnEndTests.setOpaque(SET_OPAQ);
            selectAllTestPanel.add(btnEndTests, "gapleft 5, height 30, width 130, cell 0 0");

            btnStartTests = new JButton("Testi Başlat");
            btnStartTests.setOpaque(SET_OPAQ);
//            btnStartTests.setForeground(Color.CYAN);
            selectAllTestPanel.add(btnStartTests, "gapleft 10, height 30, width 130, cell 0 0");

            cboxSelectAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    AbstractButton abstractButton = (AbstractButton) e.getSource();
                    boolean isChecked = abstractButton.getModel().isSelected();

                    for (int i = 0; i < getComponents().length; i++) {

                        if ( getComponents()[i] instanceof  JPanel){
                            JPanel panel = ((JPanel) getComponents()[i]);

                            if (( panel.getComponents()[0]).isEnabled()){// enable ise seçsin, test tekrarında problem yaşamayız
                                ((JCheckBox) panel.getComponents()[0]).setSelected(isChecked);
                                logger.info(((JCheckBox) panel.getComponents()[0]).getText()+": "+((JCheckBox) panel.getComponents()[0]).isSelected());
                            }

                        }
                    }
                }
            });


            btnEndTests.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // tıklanınca..
                    // 1. test bilgilerinin tutulduğu listi yazdır
                    // 2. listeyi clear et
                    // 3. tüm testleri enable yap
                    // 4. kendini disable yap

                    logger.info("Test sonuçları yazdırıldı: "+testResultsContext.toString());

//                    new PrintTestResults(testResultsContext);

                    testResultsContext.clear();

                    for (int i = 0; i < getComponents().length; i++) {//3
                        if ( getComponents()[i] instanceof  JPanel){
                            JPanel panel = ((JPanel) getComponents()[i]);
                            (panel.getComponents()[0]).setEnabled(true);
                            ((JCheckBox)(panel.getComponents()[0])).setSelected(false);
                            ((JCheckBox)(panel.getComponents()[0])).setForeground(Color.black);

                            if (panel.getComponents()[2] instanceof  JLabel){
                                ((JLabel)(panel.getComponents()[2])).setVisible(false);
                            }
                        }
                    }

                    cboxSelectAll.setSelected(false);
                    btnEndTests.setEnabled(false);
                    btnStartTests.setEnabled(true);
//                    MyLogger.logScreenData.removeAllElements();// log ekranını temizle
                    try {
                        MyLogger.textPaneLogData.remove(0, MyLogger.textPaneLogData.getLength());// log ekranını temizle
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }

                    MainApplication.SAYI = 1;//bu test için bunu sil

                }
            });


            btnStartTests.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    boolean isAnySelected = false;// todo enable kontrolü yapmamız gerek
                    for (int i = 0; i < getComponents().length - 1; i++) {

                        if(((JCheckBox)((JPanel)getComponent(i)).getComponent(0)).isSelected()){
                            isAnySelected = true;
                            break;
                        }
                    }

                    if (!isAnySelected){
                        JOptionPane.showMessageDialog(null,"En az bir adet test seçin!");
                        return;

                    }else{

                        // hangi testlerin cbx işaretli, tespit et testResultContext'e ekle
                        // cbx işareti kaldır
                        for (int i = 0; i < getComponents().length -1 ; i++) {// -1: "tümTestiseç" paneli hariç

                            JPanel panel = (JPanel) getComponents()[i];
                            JCheckBox cbxTest = (JCheckBox) panel.getComponent(0);
                            cbxTest.setForeground(Color.gray);

                            if (cbxTest.isSelected() && cbxTest.isEnabled()){

                                Map<EventContextParam, Object> testContext = new LinkedHashMap<EventContextParam, Object>();
                                testContext.put(EventContextParam.TEST_NAME, cbxTest.getName());
                                testContext.put(EventContextParam.TEST_STATUS, TestStatus.FAIL);
                                testContext.put(EventContextParam.TEST_RUNNABLE, true);
                                testResultsContext.put(cbxTest.getName(), testContext); // testname, testcontext

                                cbxTest.setForeground(Color.white);

                            }else {

                                // fail olan test tekrar koşulmak istenmezse
                                if (cbxTest.isEnabled() && !cbxTest.isSelected()){

                                    Map<EventContextParam, Object> testContext = (Map<EventContextParam, Object>) testResultsContext.get(cbxTest.getName());
                                    if (testContext != null){
                                        testContext.put(EventContextParam.TEST_RUNNABLE, false);
                                    }
                                }

                            }

                            cbxTest.setSelected(false);
                            cbxTest.setEnabled(false);

                        }

                        // "tümünü seç" panel elementleri disable
                        cboxSelectAll.setSelected(false);
                        cboxSelectAll.setEnabled(false);
                        cboxSelectAll.setForeground(Color.gray);
                        btnStartTests.setEnabled(false);
                        btnEndTests.setEnabled(false);

                        startTests();

                    }
                }
            });
        }

        return selectAllTestPanel;
    }

    private void startTests(){

        SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                /////// bu kısımda testleri metoda gönder//////
                Iterator iterator = testResultsContext.entrySet().iterator();
                while (iterator.hasNext()){

                    Map.Entry entry = (Map.Entry) iterator.next();
                    Map<EventContextParam, Object> map = (Map<EventContextParam, Object>) entry.getValue();//testContext'imiz
                    boolean isFail = map.get(EventContextParam.TEST_STATUS).equals(TestStatus.FAIL) ? true : false;
                    boolean isRunnable = map.get(EventContextParam.TEST_RUNNABLE).equals(true) ? true : false;// fail olan testi tekrar edilsinmi?

                    // status failse şu teste git..
                    if (entry.getKey().equals(TestOrder.MODEM_ERISIM_TESTI.name()) && isFail && isRunnable ){
                        startProgressBar(TestOrder.MODEM_ERISIM_TESTI);// progress barı çalıştır..
                        entry.setValue(new ModemErisimTesti().execute(map));
                        stopProgressBar(TestOrder.MODEM_ERISIM_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.LAN_PORTLARI_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.LAN_PORTLARI_TESTI);// progress barı çalıştır..
                        entry.setValue(new LanPortlariTesti().execute(map));
                        stopProgressBar(TestOrder.LAN_PORTLARI_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.MODEM_LOGIN_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.MODEM_LOGIN_TESTI);// progress barı çalıştır..
                        entry.setValue(new ModemLoginTesti().execute(map));
                        stopProgressBar(TestOrder.MODEM_LOGIN_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.FIRMWARE_KONTROL_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.FIRMWARE_KONTROL_TESTI);// progress barı çalıştır..
                        entry.setValue(new FirmwareKontrolTesti().execute(map));
                        stopProgressBar(TestOrder.FIRMWARE_KONTROL_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.WIFI_ISLEVSELLIK_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.WIFI_ISLEVSELLIK_TESTI);// progress barı çalıştır..
                        entry.setValue(new WifiIslevsellikTesti().execute(map));
                        stopProgressBar(TestOrder.WIFI_ISLEVSELLIK_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.INTERNET_BAGLANTI_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.INTERNET_BAGLANTI_TESTI);// progress barı çalıştır..
                        entry.setValue(new InternetBaglantiTesti().execute(map));
                        stopProgressBar(TestOrder.INTERNET_BAGLANTI_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.LED_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.LED_TESTI);// progress barı çalıştır..
                        entry.setValue(new LedTesti().execute(map));
                        stopProgressBar(TestOrder.LED_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                    if (entry.getKey().equals(TestOrder.RESET_BUTON_TESTI.name()) && isFail && isRunnable){
                        startProgressBar(TestOrder.RESET_BUTON_TESTI);// progress barı çalıştır..
                        entry.setValue(new ResetButonTesti().execute(map));
                        stopProgressBar(TestOrder.RESET_BUTON_TESTI, map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS));// progress barı çalıştır..
                    }

                } //test işlemleri bitti..


                // program buraya geldiğinde tüm testler disable durumda
                // Tüm testleri enable yap
                // success olanları disable yap
                // Böylece başlangıçta seçilmeyen testlerde seçilebilecek
                for (int i = 0; i < getComponents().length -1 ; i++) {// -1: "tümTestiseç" paneli hariç

                    JPanel panel = (JPanel) getComponents()[i];
                    JCheckBox cbxTest = (JCheckBox) panel.getComponent(0);
                    cbxTest.setSelected(false);
                    cbxTest.setEnabled(true);
                    cbxTest.setForeground(Color.black);
                }

                cboxSelectAll.setSelected(false);
                cboxSelectAll.setEnabled(true);
                cboxSelectAll.setForeground(Color.black);
                btnEndTests.setEnabled(true);
                btnStartTests.setEnabled(true);

                // şimdi başarılı geçen testleri disable yap
                // eğer hepsi başarılı ise tümünüseç cbx disable olsun
                // sayaç ile yap
                int countSuccessTest = 0;
                Iterator iterate = testResultsContext.entrySet().iterator();

                while (iterate.hasNext()){

                    Map.Entry entry = (Map.Entry) iterate.next();
                    Map<EventContextParam, Object> map = (Map<EventContextParam, Object>) entry.getValue();

                    if (map.get(EventContextParam.TEST_STATUS).equals(TestStatus.SUCCESS)){
                        //success'i bul disable yap
                        for (int i = 0; i < getComponents().length -1 ; i++) {// -1: "tümTestiseç" paneli hariç

                            JPanel panel = (JPanel) getComponents()[i];
                            if(entry.getKey().equals(panel.getName())){

                                JCheckBox cbox = (JCheckBox) panel.getComponent(0);
                                cbox.setEnabled(false);
                                cbox.setForeground(Color.gray);
                                countSuccessTest++;
                                break;
                            }
                        }
                    }
                }

                if (TestOrder.values().length != countSuccessTest){
                    cboxSelectAll.setEnabled(true);//tüm testler başarılı değilse enable
                    btnStartTests.setEnabled(true);
                }else{
                    cboxSelectAll.setEnabled(false);//tüm testler başarılı ilse disable
                    cboxSelectAll.setForeground(Color.gray);
                    btnStartTests.setEnabled(false);
                }


                return null;
            }
        };

        worker.execute();
    }

    private void startProgressBar(final TestOrder currentTest){

        SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {

                for (int i = 0; i < getComponents().length - 1; i++) {
                    JPanel panel = (JPanel) getComponent(i);
                    if (panel.getName().equals(currentTest.name())){

                        //cbox font bold
                        JCheckBox cbox = (JCheckBox) panel.getComponent(0);
                        Font font = new Font(cbox.getFont().getName(),Font.BOLD, cbox.getFont().getSize()+4);
                        cbox.setFont(font);
                        cbox.setForeground(Color.green);

                        //progressbar start
                        JProgressBar bar = (JProgressBar) panel.getComponent(1);
                        bar.setEnabled(true);
                        bar.setVisible(true);
                        bar.setIndeterminate(true);

                        //res label hidden
                        JLabel label = (JLabel) panel.getComponent(2);
                        label.setVisible(false);

                        break;
                    }
                }
                return null;
            }
        };
        worker.run();
    }

    private void stopProgressBar(final TestOrder currentTest, final boolean result){

        SwingWorker<Void,Void> worker = new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {

                for (int i = 0; i < getComponents().length - 1; i++) {
                    JPanel panel = (JPanel) getComponent(i);
                    if (panel.getName().equals(currentTest.name())){

                        //cbox font bold
                        JCheckBox cbox = (JCheckBox) panel.getComponent(0);
                        Font font = new Font(cbox.getFont().getName(), Font.BOLD, cbox.getFont().getSize()-4);
                        cbox.setFont(font);
                        cbox.setForeground(Color.gray);

                        //progres bar finish
                        JProgressBar bar = (JProgressBar) panel.getComponent(1);
                        bar.setEnabled(false);
                        bar.setVisible(false);
                        bar.setIndeterminate(false);

                        //res label ?
                        JLabel label = (JLabel) panel.getComponent(2);
                        ImageIcon ico = result ? success : fail;
                        label.setIcon(ico);
                        label.setVisible(true);

                        break;
                    }
                }
                return null;
            }
        };
        worker.run();
    }

}
