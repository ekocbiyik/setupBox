package settopbox.utilities;

import junit.framework.Assert;
import net.egemsoft.settopbox.enums.EventContextParam;
import net.egemsoft.settopbox.enums.TestOrder;
import net.egemsoft.settopbox.enums.TestStatus;
import net.egemsoft.settopbox.utilities.PrintTestResults;
import net.egemsoft.settopbox.utilities.PrintTestResults2TextFile;
import org.junit.Test;

import java.util.*;

/**
 * Created by enbiya on 25.04.2016.
 */
public class PrintTestResults2TextFileTest {


    @Test
    public void init(){

        Map<String, Object> testResultsContext = new HashMap<String, Object>();


        for (TestOrder i : TestOrder.values()) {


            if (i.name() == TestOrder.WIFI_ISLEVSELLIK_TESTI.name() || i.name() == TestOrder.RESET_BUTON_TESTI.name() ){

                Map<EventContextParam, Object> testContext = new LinkedHashMap<EventContextParam, Object>();
                testContext.put(EventContextParam.TEST_NAME, i.name());
                testContext.put(EventContextParam.TEST_STATUS, TestStatus.FAIL);
                testContext.put(EventContextParam.TEST_RUNNABLE, true);
                testContext.put(EventContextParam.TEST_CONTEXT, i.getValue()+" Başarısız geçti!");
                testResultsContext.put(i.name(), testContext);

            } else{

                Map<EventContextParam, Object> testContext = new LinkedHashMap<EventContextParam, Object>();
                testContext.put(EventContextParam.TEST_NAME, i.name());
                testContext.put(EventContextParam.TEST_STATUS, TestStatus.SUCCESS);
                testContext.put(EventContextParam.TEST_RUNNABLE, true);
                testContext.put(EventContextParam.TEST_CONTEXT, i.getValue()+" Başarılı geçti...");
                testResultsContext.put(i.name(), testContext);
            }


        }

        for (int i = 0; i < 61; i++) {

            new PrintTestResults2TextFile(testResultsContext);
        }

        Assert.assertNotNull(testResultsContext);

    }



}
