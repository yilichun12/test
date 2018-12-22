package com.example.test;

import org.springframework.util.StringUtils;

public class StringSpilt {

    public static void main(String[] args) {
        String s = "1-2-3-4-5";

        String [] ss = StringUtils.delimitedListToStringArray(s,"-");

    }
}
