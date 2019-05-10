package com.safaricom.hackathon.ossautomation.utilities;

import java.util.UUID;

public class Utilities {

    public static String generateUserCode() {
        return UUID.randomUUID().toString();
    }
}
