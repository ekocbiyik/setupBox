package net.egemsoft.settopbox.testsManagement.modemErisimTestCore;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.utilities.MyLogger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 04.04.2016.
 */
public class ModemErisimTesti {

    final static MyLogger logger = new MyLogger(ModemErisimTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){


        logger.info("Modeme erişiliyor...");
        delay();
        logger.success("Modeme erişim testi başarılı!");

        context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
        context.put(EventContextParam.TEST_CONTEXT, "Modem Erişim Testi Başarılı bitti");// test içerikleri

        delay();
        return context;
    }

    private void delay(){
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(8)+4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
