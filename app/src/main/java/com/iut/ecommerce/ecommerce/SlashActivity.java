package com.iut.ecommerce.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static java.lang.Thread.sleep;

public class SlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("fA", "Attente");

                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Log.i("fA", "Fin d'attente");
                    Intent intent = new Intent(SlashActivity.this.getBaseContext(), BoutiqueActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        new Thread(runnable).start();

    }
}
