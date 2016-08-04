package net.egemsoft.settopbox.utilities;

import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestStatus;

import java.awt.*;
import java.awt.print.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Created by enbiya on 11.04.2016.
 */
public class PrintTestResults{


    public PrintTestResults(final Map<String, Object> testResultsContext){

        if (testResultsContext != null){


            final StringBuilder builder = new StringBuilder();
            builder.append("Tarih:")
                    .append(SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("tr")).format(new Date()).toUpperCase()).append("\n")
                    .append("Bayi: Egemsoft").append("\n")
                    .append("Marka: Huawei").append("\n")
                    .append("Model: HG658v2").append("\n")
                    .append("Seri No: SN1234567890").append("\n\n");


            Set<String> keys = testResultsContext.keySet();

            String cahceForFailedTests = "";
            for (String key : keys) {

                Map<EventContextParam, Object> currTest = (Map<EventContextParam, Object>) testResultsContext.get(key);
                TestStatus testStatus = (TestStatus) currTest.get(EventContextParam.TEST_STATUS);
                String sonuc = testStatus.equals(TestStatus.SUCCESS) ? "Başarılı" : "Başarısız";

                if (!sonuc.equals("Başarılı")){

                    cahceForFailedTests += "\n" +
                            currTest.get(EventContextParam.TEST_NAME) + ": " + sonuc + "\n" +
                            "\t" + currTest.toString().replace(", ","\n\t ").toLowerCase() + "\n";

//                    builder.append("\n");
//                    builder.append(currTest.get(EventContextParam.TEST_NAME)).append(": " + sonuc).append("\n");
//                    builder.append("\t").append(currTest.toString().replace(", ","\n\t ").toLowerCase());
//                    builder.append("\n\n");

                }else {
                    builder.append(currTest.get(EventContextParam.TEST_NAME)).append(": " + sonuc).append("\n");
                }
            }

            builder.append(cahceForFailedTests);


            PrinterJob pj = PrinterJob.getPrinterJob();
            PageFormat pf = pj.defaultPage();
            Paper paper = new Paper();
            double margin = 10;// sayfa kenar boşluğu
            paper.setImageableArea(margin, margin, paper.getWidth()-margin*2, paper.getHeight()-margin*2);
            pf.setPaper(paper);

            pj.setPrintable(new Printable(){
                public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

                    if (pageIndex != 0)
                        return NO_SUCH_PAGE;

                    Graphics2D g2 = (Graphics2D) g;
                    g2.setFont(new Font("Serif", Font.PLAIN, 10));
                    g2.setPaint(Color.black);
                    myDrawString(g2, builder.toString(), 20, 20);// yazı başlangıç koor.

                    return PAGE_EXISTS;
                }
            }, pf);

            try {

                pj.print();
                System.out.println("Test Sonuçları: \n\n" + builder);

            } catch (PrinterException e) {
                e.printStackTrace();
            }

        }//..

    }

    private void myDrawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")){

            if (line.contains("\t")){
                g.drawString(line, x+5, y += g.getFontMetrics().getHeight());

            }else {
                g.drawString(line, x, y += g.getFontMetrics().getHeight());
            }

        }
    }

}
