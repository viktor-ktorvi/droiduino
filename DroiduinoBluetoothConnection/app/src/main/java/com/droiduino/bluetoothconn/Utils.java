package com.droiduino.bluetoothconn;

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
}
