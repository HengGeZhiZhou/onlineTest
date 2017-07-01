package HelloSpringMVC.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class TimestampUtil {
    public static String getTimestamp(){
        Date date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyMM");
        return dateFormat.format(date);
    }
}
