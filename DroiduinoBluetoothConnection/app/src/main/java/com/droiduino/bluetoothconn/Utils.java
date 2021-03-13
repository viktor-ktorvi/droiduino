package com.droiduino.bluetoothconn;

import android.os.Message;

public class Utils {
    // no init, no instance
    private Utils(){}

    public static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public static void handleMessage(Message msg){

    }
}
