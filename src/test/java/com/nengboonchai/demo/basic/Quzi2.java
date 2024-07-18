package com.nengboonchai.demo.basic;

import org.postgresql.gss.GSSOutputStream;

import java.util.Arrays;
import java.util.List;

public class Quzi2 {
    public static void main(String[] args) {
        Integer[] numbers = new Integer[] { 1, 2, 3 };
        List<Integer> list = Arrays.asList(numbers);

        String[] strs = new String[]{};
        List<String> list2 = Arrays.asList(strs);


        int k = 3;
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer st =new StringBuffer(str);
        StringBuilder sb =new StringBuilder("ok");
        StringBuilder sb2 = new StringBuilder("333");
        sb2= sb;

        System.out.println(sb2);
        for(int i=0;i<k;i++ ){
            Character ch = st.charAt(0);
            st.deleteCharAt(0);
            st = new StringBuffer(st.append(ch));
            System.out.println(st);
        }
    }
}
