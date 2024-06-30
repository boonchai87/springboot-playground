package com.nengboonchai.demo.basic;

public class Quzi2 {
    public static void main(String[] args) {
        int k = 3;
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer st =new StringBuffer(str);
        for(int i=0;i<k;i++ ){
            Character ch = st.charAt(0);
            st.deleteCharAt(0);
            st = new StringBuffer(st.append(ch));
            System.out.println(st);
        }
    }
}
