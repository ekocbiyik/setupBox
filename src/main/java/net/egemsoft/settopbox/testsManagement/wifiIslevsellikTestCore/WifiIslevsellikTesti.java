package net.egemsoft.settopbox.testsManagement.wifiIslevsellikTestCore;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.ui.MainApplication;
import net.egemsoft.settopbox.utilities.MyLogger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 04.04.2016.
 */
public class WifiIslevsellikTesti {

    final static MyLogger logger = new MyLogger(WifiIslevsellikTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){


        logger.info("Wifi işlevsellik testi başlatılıyor...");
        delay();

        logger.info("SSID ve parola bilgileri alınıyor...");
        delay();

        logger.info("Kablosuz ağa bağlanılıyor...");
        delay();
        delay();

        if (MainApplication.SAYI == 1){

            logger.error("Bağlantı kurulamadı!");
            logger.error("Wifi işlevsellik testi başarısız!");

            context.put(EventContextParam.TEST_STATUS, TestStatus.FAIL);
            context.put(EventContextParam.TEST_CONTEXT, "Wifi Işlevsellik Testi Başarısız bitti");// test içerikleri

            logger.error(context);

        }else {

            logger.info("Ağ bağlantısı kuruldu!");
            logger.success("Wifi işlevsellik testi başarılı!");

            context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
            context.put(EventContextParam.TEST_CONTEXT, "Wifi Işlevsellik Testi Başarılı bitti");// test içerikleri
        }

        delay();
        return context;
    }



    private void delay(){
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(6)+3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
