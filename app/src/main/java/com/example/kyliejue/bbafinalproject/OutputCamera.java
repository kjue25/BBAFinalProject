package com.example.kyliejue.bbafinalproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by aashnashroff on 3/14/18.
 */

public class OutputCamera implements Output {
    private OutputCameraActivity context;
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public OutputCamera(OutputCameraActivity context, ImageView mImageView){
        this.context = context;
        this.mImageView = mImageView;
    }

    @Override
    public void onTrigger(Context context){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(this.context.getPackageManager()) != null) {
            this.context.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.mImageView.setImageBitmap(imageBitmap);
        }
    }
}

