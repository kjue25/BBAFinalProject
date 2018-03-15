package com.example.kyliejue.bbafinalproject;

import android.content.Context;

import java.io.Serializable;

public interface Output extends Serializable {
    void onTrigger(Context context);
}
