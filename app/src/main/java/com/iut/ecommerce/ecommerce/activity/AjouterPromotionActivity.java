package com.iut.ecommerce.ecommerce.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.iut.ecommerce.ecommerce.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.String.valueOf;

public class AjouterPromotionActivity extends AppCompatActivity {

    TextView tv_pourcentage;
    EditText et_pourcentage;
    Button boutonValider;
    Spinner listeProduit;
    TextView dateDebut;
    DatePickerDialog datePickerDialogDebut;
    //private Button datePickerDebut;
    //private Button datePickerFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_promotion);
        tv_pourcentage = (TextView) findViewById(R.id.tv_pourcentage);
        et_pourcentage = (EditText) findViewById(R.id.et_pourcentage);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        listeProduit = (Spinner) findViewById(R.id.list_produit);
        dateDebut = (TextView) findViewById(R.id.dateDebut);
        //datePickerDebut = (Button) findViewById(R.id.button_dateDebut);
        //datePickerFin = (Button) findViewById(R.id.button_dateFin);
        //Initialization du spinner
        List<String> list = new ArrayList<String>();
        list.add("chaussette");
        list.add("t-shirt");

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listeProduit.setAdapter(spinnerArrayAdapter);

        dateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear= c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialogDebut= new DatePickerDialog(AjouterPromotionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateDebut.setText(dayOfMonth+ "/" + (month+1) + "/"+ + year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialogDebut.show();
            }
        });
    }

    /*public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(),"datePicker");

    }*/



}
