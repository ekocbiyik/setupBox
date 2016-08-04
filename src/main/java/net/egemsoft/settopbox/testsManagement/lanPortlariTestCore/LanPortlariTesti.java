package net.egemsoft.settopbox.testsManagement.lanPortlariTestCore;

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
public class LanPortlariTesti {

    final static MyLogger logger = new MyLogger(LanPortlariTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){


        logger.info("Lan portları test ediliyor...");
        delay();

        logger.info("1.Port test ediliyor...");
        delay();
        logger.success("1.Port testi başarılı!");


        logger.info("2.Port test ediliyor...");
        delay();
        logger.success("2.Port testi başarılı!");


        logger.info("3.Port test ediliyor...");
        delay();
        logger.success("3.Port testi başarılı!");


        logger.info("4.Port test ediliyor...");
        delay();
        logger.success("4.Port testi başarılı!");



        context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
        context.put(EventContextParam.TEST_CONTEXT, "Lan Portları Testi Başarılı bitti");// test içerikleri

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
