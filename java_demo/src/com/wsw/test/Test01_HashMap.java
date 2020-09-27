package com.wsw.test;


import java.util.HashMap;

public class Test01_HashMap {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", "王一");
        String value = hashMap.put("name", "王二");
        System.out.println(value);

    }
}
