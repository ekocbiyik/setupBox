package settopbox;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by enbiya on 28.10.2016.
 */
public class TestUUID {


    @Test
    public void testID() {

        while (true) {

            String myId = UUID.randomUUID().toString();

            if (myId.equalsIgnoreCase(UUID.randomUUID().toString()))
                System.out.println(myId);
        }
    }

    @Test
    public void testText() {

        String txt = "";

        for (int i = 0; i < 10000; i++) {
            txt += i;
        }

        System.out.println(txt);

    }


}
