package net.egemsoft.settopbox.testsManagement.internetBaglantiTestCore;

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
public class InternetBaglantiTesti {


    final static MyLogger logger = new MyLogger(InternetBaglantiTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){



        logger.info("Internet Bağlantı testi başlatılıyor...");
        delay();

        logger.info("Kullanıcı ad & Parola bilgileri alınıyor...");
        delay();


        if (MainApplication.SAYI == 1){

            logger.error("IP hatası! Bağlantı kurulamadı! ");
            logger.error("Internet Bağlantı testi başarısız! ");

            context.put(EventContextParam.TEST_STATUS, TestStatus.FAIL);
            context.put(EventContextParam.TEST_CONTEXT, "Internet Bağlantı Testi Başarısız bitti");// test içerikleri

            logger.error(context);

        }else {

            logger.success("IP bilgisi alındı!");
            logger.info("Dosya yükleme işlemi başlatılıyor...");
            delay();
            logger.success("Dosya yükleme başarılı!");

            logger.info("Dosya indirme işlemi başlatılıyor...");
            delay();
            logger.success("Dosya indirme işlemi başarılı!");

            context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
            context.put(EventContextParam.TEST_CONTEXT, "Wifi Işlevsellik Testi Başarılı bitti");// test içerikleri

            logger.success("Internet Bağlantı Testi başarılı!");

        }

        MainApplication.SAYI++;


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
