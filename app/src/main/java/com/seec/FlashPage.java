package com.seec;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;


/*
* Authors: Jesse Jeun
* Date: 10-19-2015
* Description: FlashPage Activity - Activity page that will display the apps logo as the opening screen.
*/


public class FlashPage extends AppCompatActivity {

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //thought this would remove the nav bar, no luck
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_page);

            timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                submit();
            }
        }.start();
    }


    // Method to create intent and activate it
    public void submit(){

        // creating object intent of class Intent that will redirect to MainActivity page
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);  // starting the intent
    }

}
