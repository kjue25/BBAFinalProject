package com.example.kyliejue.bbafinalproject;

import java.io.Serializable;

// TODO: Consider if Parcelable should be used instead of Serializable
public interface Output extends Serializable {
    void onTrigger();
}