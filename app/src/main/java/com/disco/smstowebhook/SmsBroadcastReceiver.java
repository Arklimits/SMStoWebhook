package com.disco.smstowebhook;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    // creating a variable for a message listener interface on below line.
    private static SmsListenerInterface mListener;
    List<String> specificNumbers;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SmsBroadcastReceiver", "CALLED");

        loadSpecificNumbers(context);

        // getting bundle data on below line from intent.
        Bundle data = intent.getExtras();
        // creating an object on below line.
        Object[] pdus = (Object[]) data.get("pdus");
        // running for loop to read the sms on below line.
        for (Object o : pdus) {
            // getting sms message on below line.
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) o);

            String sender = smsMessage.getDisplayOriginatingAddress();
            String message = smsMessage.getMessageBody();

            if (specificNumbers.contains(sender)) {
                Log.d("SmsBroadcastReceiver", message);
                sendToSlack(context, message);
            }
        }
    }

    private void loadSpecificNumbers(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // Retrieve the selected numbers (default to empty set)
        Set<String> selectedNumbers = sharedPreferences.getStringSet("number", new HashSet<>());

        // Convert Set to List
        specificNumbers = new ArrayList<>(selectedNumbers);

        Log.d("SmsBroadcastReceiver", "Selected Numbers: " + specificNumbers);
    }

    public void sendToSlack(Context context, String message) {
        String data = "{\"text\": \"" + message + "\"}";
        Log.d("SmsBroadcastReceiver", data);
        SendToSlack sendToSlack = new SendToSlack(context);
        sendToSlack.execute(data);
    }
}