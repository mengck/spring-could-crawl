package com.mengck.crawl.user;

import com.alibaba.fastjson2.JSON;

import java.util.*;

public class LRUTest {
    static Map<Integer,String> mp = new HashMap<>();

    public static void main(String[] args) {
//        if(mp.containsKey(1)){
//            String a = "diee";
//            char[] chars = a.toCharArray();
//            Stack<Character> stack = new Stack<>();
//
//            System.out.println(a.substring(1,2));
//        }
//        int[] a = new int[2];
//        a[0] = 3;
//        a[1] = 2;
//        Arrays.sort(a);
//
//        Set<Integer> set = new HashSet<Integer>();
//
//        for(int i= 0;i<10;i++){
//            set.add(i);
//        }
//        Object[] list = set.toArray();
//
//        Integer result = 0;
//
//        for(int i=0;i<a.length;i++){
//            System.out.println(a[i]);
//        }
//        System.out.println(LCS("1AB2345CD","12345EF"));


        short s1 = 1;
        s1 = (short) (s1+1);
        s1+=1;

        int[] list = new int[]{1,2,3,6,4,7,9,8,19,12};
        sort(list);
    }



    public static String LCS (String str1, String str2) {

        // write code here
        int[][] dp = new int[str1.length()][str2.length()];
        int max = 0 ;
        int pos = 0;

        for (int i = 0; i < str1.length(); i++) {

            for (int j = 0; j < str2.length(); j++) {

                if (str1.charAt(i) == str2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    pos = i;
                }
            }
        }
        return str1.substring(pos+1-max, pos + 1);
    }


    public static void sort(int[] list){

        sortMaopao(list);
        System.out.println(JSON.toJSONString(list));
        sortKP(list);
        System.out.println(JSON.toJSONString(list));
    }

    private static void sortKP(int[] list) {

        if(list== null){
            return;
        }

        sortKP(list,0,list.length-1);

    }

    private static void sortKP(int[] list, int left, int right) {
        if(left>=right){
            return;
        }
        int p = pation(list,left,right);
        sortKP(list,left,p);
        sortKP(list,p+1,right);
    }

    private static int pation(int[] list, int left, int right) {


        int key = list[left];

        int start = left;
        int end = right;

        while (start<end){
            // 从右边开始找比基准值大的数
            while (start<end && list[end] >= key){
                end --;
            }
            // 从左边开始找比基准值要小的数据
            while (start<end && list[start] <= key){
                start ++;
            }
            // 如果没有过接，则证明 list[start] > list[end]
            if(start<end){
                int tem = list[start];
                list[start] = list[end];
                list[end]  = tem;
            }
        }

        list[left] = list[end];
        list[end] = key;

        return end;

    }

    private static int siglePation(int[] list, int left, int right){
        int key = list[left];
        int mark = left;
        for (int i = left +1;i<right;i++){
            if(list[i]<key){
                mark ++;
                int tem = list[mark];
                list[mark] = list[i];
                list[i] = tem;
            }
        }
        list[left] = list[mark];
        list[mark] = key;
        return mark;
    }


    private static void sortMaopao(int[] list) {

        for (int i=0 ;i<list.length;i++){
            for (int j= 0 ;j<list.length-1-i;j++){
                if(list[j]> list[j+1]){
                    int tem = list[j];
                    list[j] = list[j+1];
                    list[j+1] = tem;
                }
            }
        }
    }
}
