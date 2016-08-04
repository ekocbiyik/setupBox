package net.egemsoft.settopbox.enums;

/**
 * Created by enbiya on 25.03.2016.
 */
public enum TestOrder {

    // testler App.Window altında getCenterPanel() içinde bu sırada dinamik olarak oluşturuluyor
    //yeni bir test eklenmek istenirse buraya test Enum'ını ve adını ekleyin daha sonra ilgili class'ları oluşturun

    MODEM_ERISIM_TESTI("Modem Erişim Testi"),
    LAN_PORTLARI_TESTI("Lan Portları Testi"),
    MODEM_LOGIN_TESTI("Modem Login Testi"),
    FIRMWARE_KONTROL_TESTI("Firmware Kontrol Testi"),
    WIFI_ISLEVSELLIK_TESTI("Wifi İşlevsellik Testi"),
    INTERNET_BAGLANTI_TESTI("Internet Bağlantı Testi"),
    LED_TESTI("LED Testi"),
    RESET_BUTON_TESTI("Reset Buton Testi");

    private String value;


    private TestOrder(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}
