package net.egemsoft.settopbox.ui;


import net.egemsoft.settopbox.cofiguration.AdapterSettings;

/**
 * Created by enbiya on 21.03.2016.
 */
public class MainApplication {

    public static int SAYI = 1;
    public static boolean ALL_OPAQ_SETTINGS = false;


    public static void main(String[] args) {


        boolean uygulamaZatenCalisiyorMu = false;

        /** en son aşama */
        if (!uygulamaZatenCalisiyorMu) {
            startApplication();
        }

    }


    private static void startApplication() {

        initAdapters();
        initWindow();
        sendLogs();
        deleteOldLogs();

    }

    private static void initAdapters() {

        if (!new AdapterSettings().isAdaptersOK()) {


            /** sonuç false ise adaptör ayarlarını yapılması için kullanıcıya hata uyarısı ver (mac_conf uyarısı gibi)
             *  programı sonlandır
             *
             * */
        }

    }

    private static void initWindow() {

        new ApplicationWindow(); /** arayüzü başlat */
//        new ApplicationWindow2(); // test için
    }

    private static void sendLogs() {
        /** burada test edilmiş modem logları arkaplanda Thread ile sunucuya göndersin */
    }

    private static void deleteOldLogs() {
        /** burada sunucuya gönderilmiş ve 3 aylık süresi dolan logların silinmesi için thread çalıştır */
    }


}
