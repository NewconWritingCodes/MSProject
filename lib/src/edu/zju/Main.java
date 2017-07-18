package edu.zju;

/**
 * Created by liukang on 2017/7/18.
 */

import static edu.zju.Spiciness.*;
import static edu.zju.OzWitch.*;

public class Main {

    public static void main(String[] args) {


        Spiciness s1 =Spiciness.HOT;
        switchEnum(s1);
        Spiciness s2 =Spiciness.MILD;
        switchEnum(s2);

        for(Spiciness s :Spiciness.values()){
            System.out.println(HOT);
        }

        System.out.println();

        System.out.println(WEST.getDescription());
    }
    public static void switchEnum(Spiciness spiciness){
        switch (spiciness){
            case HOT:
                System.out.println("HOT");
                break;
            case MILD:
                System.out.println("MILD");
                break;
        }


    }


}
