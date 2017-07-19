package edu.zju;

/**
 * Created by liukang on 2017/7/18.
 */

import static edu.zju.Spiciness.*;
import static edu.zju.OzWitch.*;

import static edu.zju.Main.*;

enum MyColor{
    RED,GREEN,BLUE;

    @Override
    public String toString() {
        String id = name();
        return "Color is "+id;
    }
}
public class Main {
    static MyColor myColor = MyColor.RED;
    public static void changeColor(){
        switch (myColor){
            case RED:
                System.out.println(myColor);
                myColor = MyColor.BLUE;
                break;
            case BLUE:
                System.out.println(myColor);
                myColor = MyColor.GREEN;
                break;
            case GREEN:
                System.out.println(myColor);
                myColor = MyColor.RED;
                break;
        }


    }


    public static void main(String[] args) {

        for(int i=0;i<10;i++){
            Main main = new Main();
//            System.out.println(main);
            main.changeColor();
        }

    }



}
