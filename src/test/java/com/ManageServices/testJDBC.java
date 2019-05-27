package com.ManageServices;


import java.text.SimpleDateFormat;
import java.util.Date;
public class testJDBC {
    public static void main(String arg[]){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        System.out.println(date);

    }
}
