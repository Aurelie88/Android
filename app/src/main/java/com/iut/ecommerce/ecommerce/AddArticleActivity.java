package com.iut.ecommerce.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class AddArticleActivity extends AppCompatActivity {

    private TextView designation;
    private TextView reference;
    private TextView tarif;
    private EditText editDesignation;
    private EditText editReference;
    private EditText editTarif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
