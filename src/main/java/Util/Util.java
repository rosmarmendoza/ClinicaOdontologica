package Util;
import java.sql.Date;
import java.sql.Timestamp;


public class Util {
    public static Timestamp dateToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;

    }

    //Convierte un Date en un SQL Date
    public static Date utilDateToSqlDate(Date utilDate){
        long timeInMilliSeconds = utilDate.getTime();
        Date sqlDate = new Date(timeInMilliSeconds);
        return sqlDate;
    }
}
