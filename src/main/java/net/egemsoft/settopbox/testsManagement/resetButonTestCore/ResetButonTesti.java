package net.egemsoft.settopbox.testsManagement.resetButonTestCore;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.utilities.MyLogger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 04.04.2016.
 */
public class ResetButonTesti {

    final static MyLogger logger = new MyLogger(ResetButonTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){



        logger.info("Reset testi başlatılıyor...");
        delay();
        logger.info("Modem fabrika ayarlarına alınıyor...");
        delay();
        logger.info("Reset bilgileri alınıyor...");
        logger.success("Reset testi başarılı!");

        context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
        context.put(EventContextParam.TEST_CONTEXT, "Led Testi Başarılı bitti");// test içerikleri


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
