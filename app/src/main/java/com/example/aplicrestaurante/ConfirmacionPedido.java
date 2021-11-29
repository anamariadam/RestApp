package com.example.aplicrestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class ConfirmacionPedido extends MainMenu {
TextView horallegada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_pedido);

        getSupportActionBar().setTitle("RestApp");
        String bb = "#E4F4C536";
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bb)));

        Date currentTime = Calendar.getInstance().getTime();
        Time time = new Time(Time.getCurrentTimezone());
        time.setToNow();
        String hfin;
        int h = time.hour;
        int m = time.minute;
        int mfinal = m+40;
        if (mfinal >= 60 && mfinal-60 < 10) {
            mfinal = mfinal - 60;
            h = h + 1;
            hfin = String.valueOf(h) + ":0" + String.valueOf(mfinal);
        }else if(mfinal >= 60){
            mfinal = mfinal - 60;
            h = h + 1;
            hfin = String.valueOf(h) + ":" + String.valueOf(mfinal);
        }else{
            hfin = String.valueOf(h)+":"+String.valueOf(mfinal);
        }

        horallegada = findViewById(R.id.horafin);

        horallegada.setText(hfin);

    }
}