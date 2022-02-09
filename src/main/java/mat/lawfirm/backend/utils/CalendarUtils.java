package mat.lawfirm.backend.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarUtils {

    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    public static String getCurrentTimeStamp() {
        return ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

}
