package org.seckill.interview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        String[] b = new String[]{"1223", "21313", "32423"};
        List list = new ArrayList();
        list.add("hah");
        list.add("haah");
        list.add("haaah");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            String s = (String) iterator.next();
            System.out.println(s);
        }
    }


}