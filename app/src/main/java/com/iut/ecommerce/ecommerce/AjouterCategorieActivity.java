package com.iut.ecommerce.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AjouterCategorieActivity extends AppCompatActivity {
    private TextView tv_nomCategorie;
    private EditText et_nomCategorie;
    private TextView tv_visuelCategorie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);
        this.tv_nomCategorie=(TextView) this.findViewById(R.id.tv_nomCategorie);
        this.et_nomCategorie=(EditText) this.findViewById(R.id.et_nomCategorie);
        this.tv_visuelCategorie=(TextView) this.findViewById(R.id.tv_visuelCategorie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void ajouter(View v){

    }
}
