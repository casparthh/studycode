package thh.studycode.jvm;

import org.apache.commons.lang.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        Integer a = 1;
//        Integer b = 2;
//        Integer c = 3;
//        Integer d = 3;
//        Integer e = 321;
//        Integer f = 321;
//        Long g = 3L;
//        System.out.println( c == d);
//        System.out.println( e == f);
//        System.out.println( c== (a+b));
//        System.out.println( c.equals(a+b));
//        System.out.println( g == (a+b));
//        System.out.println( g.equals(a+b));
//


        Date date = new Date();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() + 1000);
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis() + 1100);
        if (timestamp.after(timestamp2)) {
            System.out.println("date: " + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss SSS"));
            System.out.println("timestamp: " + DateFormatUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss SSS"));
            System.out.println("date.after(timestamp) :" + true);
        } else {
            System.out.println("date: " + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss SSS"));
            System.out.println("timestamp: " + DateFormatUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss SSS"));
            System.out.println("date.after(timestamp) :" + false);
        }
    }
}
