package mat.lawfirm.backend.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class  CalendarUtils {

    public static String dateFormat = "dd-MM-yyyy HH:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static String ConvertMilliSecondsToFormattedDate(Long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return simpleDateFormat.format(calendar.getTime());
    }
}
