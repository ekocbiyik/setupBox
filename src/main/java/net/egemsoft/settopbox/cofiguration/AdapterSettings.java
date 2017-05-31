package net.egemsoft.settopbox.cofiguration;

import org.apache.log4j.Logger;

/**
 * Created by enbiya on 31.05.2017.
 */
public class AdapterSettings {

    /**
     *
     * bu class adaptörelerin bilgisayara takılı olup olmadığını kontrol edecek
     * eğer tüm adaptöreler takılı ve ip'leri doğruysa true, en az birinde hata varsa false dönecek
     *
     * ilk olarak /.superonline/kiosk/conf/ dizininde mac_conf.txt dosyası var mı? kontrol et
     *      eğer yoksa dosyayı oluştur ve dosya içeriğine AdapterType içindeki değişkenleri yazdır (ör: ADAPTER_INTERNET=null),
     *      dosyayı oluşturduktan sonra return false yap.
     *      eğer varsa dosya içindeki adaptörler bilgisayara takılı mı? takılıysa ipleri doğru mu?
     *
     * tüm adımlar doğru ise sonuç başarılı: true
     * en az biri hatalıysa sonuç başarısız: false
     *
     * */

    private static Logger logger = Logger.getLogger(AdapterSettings.class);

    public boolean isAdaptersOK() {

        boolean result = true;

        logger.info("yapılan adımlar bu şekilde log ile bastırılsın.");



        return result;
    }


}
