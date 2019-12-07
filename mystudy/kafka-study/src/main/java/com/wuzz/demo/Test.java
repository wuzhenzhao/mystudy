package com.wuzz.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/22
 * Time: 19:43
 * Description 描述:
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        Date date =new Date();
        System.out.println(date.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = formatter.parse("2019-01-01 12:12:12");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHH");
        String format = dateFormatter.format(parse);
        Date parse1 = dateFormatter.parse(format);
        System.out.printf(parse1.toString());
    }
}
