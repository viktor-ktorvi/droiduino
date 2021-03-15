package com.droiduino.bluetoothconn;

import android.os.Message;
import android.view.View;

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


    public static void handleMessage(MainActivity mainActivity, Message msg){
        switch (msg.what){
            case MainActivity.CONNECTING_STATUS:
                switch(msg.arg1){
                    case 1:
                        MainActivity.toolbar.setSubtitle("Connected to " + mainActivity.deviceName);
                        MainActivity.progressBar.setVisibility(View.GONE);
                        MainActivity.buttonConnect.setEnabled(true);
                        MainActivity.buttonToggle.setEnabled(true);
                        break;
                    case -1:
                        // TODO Issue: If already connected and you click on connect it
                        //  goes here and screws everything up
                        MainActivity.toolbar.setSubtitle("Device fails to connect");
                        MainActivity.progressBar.setVisibility(View.GONE);
                        MainActivity.buttonConnect.setEnabled(true);
                        break;
                }
                break;

            case MainActivity.MESSAGE_READ:
                // TODO Make string placeholders.
                String arduinoMsg = msg.obj.toString(); // Read message from Arduino
                if (Utils.isNumber(arduinoMsg)){
                    MainActivity.textViewMeasured.setText("Measured value = " + arduinoMsg);
                    if (MainActivity.sampleFlag) {
                        float val = Float.parseFloat(arduinoMsg);
                        MainActivity.realTimeChart.updateChart(val);
                        MainActivity.sampleFlag = false;
                    }
                }
                else {
                    switch (arduinoMsg.toLowerCase()) {
                        case "led is turned on":
                            MainActivity.imageView.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorOn));
                            MainActivity.buttonToggle.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorOn));
                            MainActivity.textViewInfo.setText("Arduino Message : " + arduinoMsg);
                            break;
                        case "led is turned off":
                            MainActivity.imageView.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorOff));
                            MainActivity.buttonToggle.setBackgroundColor(mainActivity.getResources().getColor(R.color.colorOff));
                            MainActivity.textViewInfo.setText("Arduino Message : " + arduinoMsg);
                            break;
                    }
                }
                break;
        }
    }
}
