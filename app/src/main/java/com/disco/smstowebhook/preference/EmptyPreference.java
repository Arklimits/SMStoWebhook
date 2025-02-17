package com.disco.smstowebhook.preference;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;


public class EmptyPreference extends Preference {

    public EmptyPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onClick() {
        super.onClick();
    }
}
