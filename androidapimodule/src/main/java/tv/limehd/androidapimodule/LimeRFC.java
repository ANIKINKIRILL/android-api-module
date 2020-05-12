package tv.limehd.androidapimodule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LimeRFC {

    public static String timeStampToRFC(long timeStamp){
        SimpleDateFormat rfc3339format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        Date date = new Date(timeStamp);
        return rfc3339format.format(date);
    }

}
