package net.egemsoft.settopbox.testsManagement.wifiIslevsellikTestCore;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by enbiya on 03.10.2016.
 */
public class CreateWifiProfile {



    public static boolean create() throws IOException {


        /////////////////////CREATE NEW WIFI PROFILE///////////////////////////////////////////////
        String ssid = "SUPERONLINE-WiFi_0058";
        String password = "superonline";
        String interfaceName = "Wireless Network Connection";
        String wifiSecMode = "WPA2PSK";
        String wifiEncMode = "AES";


        char [] chars = ssid.toCharArray();
        String hex_ssid = new String();
        for (int i = 0; i < chars.length; i++) {
            hex_ssid += Integer.toHexString(chars[i]);

        }


        String xmlProfile =
//                "<?xml version=\"1.0\"?>\n" +
                "<WLANProfile xmlns=\"http://www.microsoft.com/networking/WLAN/profile/v1\">" +
                    "<name>"+ ssid +"</name>" +
                    "<SSIDConfig>" +
                        "<SSID>" +
                            "<hex>"+ hex_ssid +"</hex>" +
                            "<name>"+ ssid +"</name>" +
                        "</SSID>" +
                    "</SSIDConfig>" +
                    "<connectionType>ESS</connectionType>" +
                    "<connectionMode>auto</connectionMode>" +
                    "<MSM>" +
                        "<security>" +
                            "<authEncryption>" +
                                "<authentication>"+ wifiSecMode +"</authentication>" +
                                "<encryption>"+ wifiEncMode +"</encryption>" +
                                "<useOneX>false</useOneX>" +
                            "</authEncryption>" +
                            "<sharedKey>" +
                                "<keyType>passPhrase</keyType>" +
                                "<protected>false</protected>" +
                                "<keyMaterial>"+ password +"</keyMaterial>" +
                            "</sharedKey>" +
                        "</security>" +
                "</MSM>" +
                "</WLANProfile>";

        File xmlFile = new File(System.getProperty("user.home") + "\\wifiProfile.xml");
        if (!xmlFile.exists())
            xmlFile.createNewFile();


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(xmlFile, true),"UTF-8"));
        out.write(xmlProfile);
        out.close();

        Runtime.getRuntime().exec("cmd /C netsh wlan add profile filename=\""+ xmlFile.getPath() +"\" interface=\""+ interfaceName +"\"");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /////////////////////CREATE NEW WIFI PROFILE///////////////////////////////////////////////




        /////////////////////CONNECT WIFI PROFILE///////////////////////////////////////////////
        Runtime.getRuntime().exec("cmd /C netsh wlan connect ssid=\""+ ssid +"\" name=\""+ ssid +"\" interface=\""+ interfaceName +"\"");
        /////////////////////CONNECT WIFI PROFILE///////////////////////////////////////////////



        return true;

    }


    public static void main(String[] args) {

//        String a = "SUPERONLINE_WiFi_0185";
//        String hex_a = "53555045524f4e4c494e455f576946695f30313835";
//
//
//        char [] chars = a.toCharArray();
//        String hex = new String();
//        for (int i = 0; i < chars.length; i++) {
//            hex += Integer.toHexString(chars[i]);
//        }

//        System.out.println(hex.equals(hex_a));


        try {
            create();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
