package net.egemsoft.settopbox.utilities;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;

import java.io.*;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by enbiya on 25.04.2016.
 */
public class PrintTestResults2TextFile {


    public PrintTestResults2TextFile(final Map<String, Object> testResultsContext){


        String filePath = "C:\\Users\\" + System.getProperty("user.name") + "\\.superonline\\kiosk\\modemler\\";

        File modemFolder = new File(filePath);
        if (!modemFolder.exists()){
            modemFolder.mkdir();
        }

        Calendar c = Calendar.getInstance();
        DateFormatSymbols dfs = new DateFormatSymbols();
        int year = c.get(Calendar.YEAR);
        String month = dfs.getMonths()[c.get(Calendar.MONTH)];

        File txtFile = new File(filePath + month+"_"+year+".txt");

        if (!txtFile.exists()) {
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //////////////^^create file^^////////////////////////////////


        LineNumberReader reader = null;
        int lineCount=0;
        try {

            reader = new LineNumberReader(new FileReader(txtFile));
            String s = "";
            while ((s = reader.readLine()) != null){
                if (s.length()==1){
                    lineCount = 0;
                }else{
                    lineCount = reader.getLineNumber();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //////////////^^get line count^^////////////////////////////////

        final StringBuilder builder = new StringBuilder();

        builder.append("{(" + ++lineCount + ")");
        builder.append("Tarih:")
                .append(SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("tr")).format(new Date()).toUpperCase()).append(", ")
                .append("Bayi:Egemsoft").append(", ")
                .append("Modem:HG658v2").append(", ")
                .append("S.No:SN1234567890").append(", ");

        Set<String> keys = testResultsContext.keySet();

        String testSonucu = "Başarılı";
        for (String key : keys) {

            Map<EventContextParam, Object> currTest = (Map<EventContextParam, Object>) testResultsContext.get(key);
            TestStatus testStatus = (TestStatus) currTest.get(EventContextParam.TEST_STATUS);
            String sonuc = testStatus.equals(TestStatus.SUCCESS) ? "Başarılı" : "Başarısız";

            if (sonuc.equals("Başarısız")){
                testSonucu = "Başarısız";
            }
        }
        builder.append("Sonuc:" + testSonucu + "}");


        BufferedWriter out = null;
        try {

            FileWriter fstream = new FileWriter(txtFile, true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(builder.toString());
            out.newLine();

        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



}
