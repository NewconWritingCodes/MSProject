package org.seckill.interview;

/**
 * Created by liukang on 2017/7/15.
 */
public class Main {

    public static int singleSort(int[] a,int start ,int end){
        int temp = a[start];
        while(start <end){
            while(start<end&&a[end]>=temp) end--;
            a[start] = a[end];
            while(start<end&&a[start]<=temp) start++;
            a[end] = a[start];
        }
        a[start] = temp;
        return start;


    }

    public static void qSort(int[] a ,int start ,int end){
        int temp = singleSort(a,start,end);
        if(start<temp) {
            qSort(a, start, temp - 1);
        }
        if(temp<end) {
            qSort(a, temp + 1, end);
        }


    }


    public static void main(String[] args){

        int[] a = {5,4,3,2,1};
        for(int i:a){
            System.out.print(i);
        }
        System.out.println();
        qSort(a,0,a.length-1);

        for(int i:a){
            System.out.print(i);
        }


    }
}
