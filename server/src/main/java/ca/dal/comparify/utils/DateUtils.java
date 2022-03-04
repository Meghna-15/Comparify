package ca.dal.comparify.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Harsh Shah
 */
public class DateUtils {

    /**
     * @author Harsh Shah
     */
    private DateUtils(){}

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    /*------------------  Instant ------------------*/

    /**
     * @return
     *
     * @author Harsh Shah
     */
    private static ZonedDateTime now(){
        return Instant.now().atZone(DEFAULT_ZONE_ID);
    }

    /*------------------  Date ------------------*/

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static Date dateNow(){
        return  Date.from(Instant.from(Instant.now().atZone(DEFAULT_ZONE_ID)));
    }

    /**
     * @param seconds
     * @return
     *
     * @author Harsh Shah
     */
    public static Date addSecondsToDateNow(long seconds){
        return new Date(dateNow().getTime() + seconds);
    }

    /*------------------  Local Date ------------------*/

    /**
     * @return
     *
     * @author Harsh Shah
     */
    public static LocalDate localNow(){
        return now().toLocalDate();
    }

    /**
     * @param seconds
     * @return
     *
     * @author Harsh Shah
     */
    public static LocalDate addSecondsToLocalNow(long seconds){
        return localNow().plus(seconds, ChronoUnit.SECONDS);
    }

    /**
     * @param days
     * @return
     *
     * @author Harsh Shah
     */
    public static LocalDate addDaysToLocalNow(long days){
        return localNow().plusDays(days);
    }

    /**
     * @param localDate
     * @return
     *
     * @author Harsh Shah
     */
    public static boolean isAfterNow(LocalDate localDate){
        return localNow().isAfter(localDate);
    }
}
