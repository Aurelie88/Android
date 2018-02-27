package com.iut.ecommerce.ecommerce.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.LENGTH_LONG;
import static java.lang.String.valueOf;

public class AjouterPromotionActivity extends AppCompatActivity {

    private TextView tv_pourcentage;
    private EditText et_pourcentage;
    private Button boutonValider;
    private Spinner listeCategorie;
    private Spinner listeArticle;
    private TextView dateDebut;
    private DatePickerDialog datePickerDialogDebut;
    private DatePickerDialog datePickerDialogFin;
    private TextView dateFin;
    private TextView tv_dateFin;
    private TextView tv_dateDebut;
    private ActiviteEnAttenteAvecResultat activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_promotion);
        tv_pourcentage = (TextView) findViewById(R.id.tv_pourcentage);
        et_pourcentage = (EditText) findViewById(R.id.et_pourcentage);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        listeCategorie = (Spinner) findViewById(R.id.list_categorie);
        listeArticle = (Spinner) findViewById(R.id.list_produit);
        dateDebut = (TextView) findViewById(R.id.dateDebut);
        dateFin = (TextView) findViewById(R.id.dateFin);
        tv_dateDebut = (TextView) findViewById(R.id.tv_dateDebut);
        tv_dateFin = (TextView) findViewById(R.id.tv_dateFin);

        // Dans un premier temps, on désactive le spinner des articles
        // il sera réactivé lors du clique sur le spinner de catégorie
        listeArticle.setEnabled(false);

        // Récupération de la liste de catégorie
        final ArrayList<Categorie> temp_categorie = CategorieView.getInstance().liste;

        // Définition de la liste de catégorie
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, temp_categorie);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listeCategorie.setAdapter(spinnerArrayAdapter);

        listeCategorie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                listeArticle.setEnabled(true);
                Categorie currentCategorie = (Categorie) parent.getItemAtPosition(position);

                // On appelle la base de données pour remplir la liste d'articles
                ArticleDao.getInstance((ActiviteEnAttenteAvecResultat) activite).filter(currentCategorie.getIdCateg());
                // Récupération de la liste des articles filtrées
                final ArrayList<Article> temp_article = ArticleView.getInstance().liste;

                // Quand c'est fini, on set notre spinner listeArtcle
                ArrayAdapter spinnerProduitArrayAdapter = new ArrayAdapter(PromotionView.getInstance().getContext(), R.layout.support_simple_spinner_dropdown_item, temp_article);
                spinnerProduitArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                listeArticle.setAdapter(spinnerProduitArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Indique l'activité appelante en attente d'un retour
        activite = (ActiviteEnAttenteAvecResultat) ArticleView.getInstance();

        dateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialogDebut = new DatePickerDialog(AjouterPromotionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dateDebut.setText(dayOfMonth + "/" + (month + 1) + "/" + +year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialogDebut.show();
            }
        });

        dateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialogFin = new DatePickerDialog(AjouterPromotionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                dateFin.setText(dayOfMonth + "/" + (month + 1) + "/" + +year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialogFin.show();
            }
        });
        boutonValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean erreur = false;
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String dateDeb = dateDebut.getText().toString();
                String dateF = dateFin.getText().toString();

                if ((et_pourcentage.getText().toString().isEmpty()) || (Integer.parseInt(et_pourcentage.getText().toString()) > 100)) {
                    //pourcentage non renseigner
                    et_pourcentage.setBackgroundColor(getColor(R.color.red));
                    erreur = true;
                } else {
                    et_pourcentage.setBackgroundColor(getColor(R.color.white));
                }
                try {
                    Date dDeb = format.parse(dateDeb);
                    Date dFin = format.parse(dateF);
                    if (getDifferenceDays(dDeb, dFin) < 0) {
                        Log.i("pb", "date inf");
                        Toast.makeText(getApplicationContext(), "la date de fin et antérieur à la date de début", LENGTH_LONG).show();
                        dateDebut.setBackgroundColor(getColor(R.color.red));
                        dateFin.setBackgroundColor(getColor(R.color.red));
                        erreur = true;
                    } else {
                        dateDebut.setBackgroundColor(getColor(R.color.white));
                        dateFin.setBackgroundColor(getColor(R.color.white));
                    }
                } catch (ParseException e) {
                    if (dateFin.getText().toString().isEmpty()) {
                        Log.i("fin", "true");
                        dateFin.setBackgroundColor(getColor(R.color.red));
                    } else {
                        Log.i("fin", "false");
                        dateFin.setBackgroundColor(getColor(R.color.white));
                    }
                    if (dateDebut.getText().toString().isEmpty()) {
                        dateDebut.setBackgroundColor(getColor(R.color.red));
                    } else {
                        dateDebut.setBackgroundColor(getColor(R.color.white));
                    }
                    e.printStackTrace();
                    erreur = true;
                }
            }
        });

    }

    public int getDifferenceDays(Date dDeb, Date dFin) {
        int daysdiff = 0;
        long diff = dFin.getTime() - dDeb.getTime();
        Log.i("time fin", valueOf(dFin.getTime()));
        Log.i("time deb", valueOf(dDeb.getTime()));
        long diffDays = diff / (24 * 60 * 60 * 1000);
        daysdiff = (int) diffDays;
        return daysdiff;
        //boutonValider.setText(Integer.toString(daysdiff));

    }


}
