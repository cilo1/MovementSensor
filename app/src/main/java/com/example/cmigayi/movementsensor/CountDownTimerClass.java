package com.example.cmigayi.movementsensor;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cmigayi on 02-Jul-16.
 */
public class CountDownTimerClass extends CountDownTimer {
    TextView textView;
    Context context;

    public CountDownTimerClass(long millisInFuture, long countDownInterval, Context context, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        this.context = context;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int progress = (int) (millisUntilFinished/1000);

        textView.setText(Integer.toString(progress));
    }

    @Override
    public void onFinish() {
        textView.setText(" Count Down Finish ");

        //String phoneNumber = "0719263180";
        String phoneNumber = "0714972587";
        String smsBody = "Hi, i am in danger, call me back asap!";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
        //context.startActivity(smsIntent);

        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
        context.startActivity(callIntent);

        Toast.makeText(context, "Calling a friend!", Toast.LENGTH_LONG).show();
    }

}
