package net.egemsoft.settopbox.testsManagement.firmwareControlTestCore;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.guiPanels.GetBottomPanel;
import net.egemsoft.settopbox.utilities.MyLogger;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 04.04.2016.
 */
public class FirmwareKontrolTesti {

    final static MyLogger logger = new MyLogger(FirmwareKontrolTesti.class);

    public Map<EventContextParam, Object> execute(Map<EventContextParam, Object> context){


        logger.info("Firmware versiyonu test ediliyor...");
        delay();
        logger.success("Desteklenen Firmware versiyonu!");

        context.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);// test başarılı
        context.put(EventContextParam.TEST_CONTEXT, "Firmware Kontrol Testi Başarılı bitti");// test içerikleri

        delay();

        return context;
    }

    private void delay(){
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(6)+2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
