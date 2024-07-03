package org.example.sharedlibrary.constant;

import java.util.UUID;

public class GenerateConstant {


    private GenerateConstant() {
    }


    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
