package com.degrizz.james.android_gkb.helloworld;

public class AppData {
    private static AppData instance = new AppData();

    //fields
    public String city;

    //methods
    private AppData() {
        city = null;
    }

    public static AppData getInstance() {
        return instance;
    }
}
