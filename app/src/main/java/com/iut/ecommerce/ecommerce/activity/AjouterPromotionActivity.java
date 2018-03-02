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
import com.iut.ecommerce.ecommerce.dao.PromotionDao;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.modele.Promotion;
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
    private ArrayList<Categorie> temp_categorie;
    private ArrayList<Article> temp_article;
    private ArrayList<Article> filtred_article;
    private ArrayAdapter spinnerCategorieAdapter;
    private ArrayAdapter spinnerArticleAdapter;
    private Date dateDebutRequete;
    private Date dateFinRequete;
    private Date dDeb;
    private Date dFin;
    private Promotion promotion= null;

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


        // Récupération de la liste de catégorie
        temp_categorie = CategorieView.getInstance().liste;
        // Définition de la liste de catégorie
        spinnerCategorieAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, temp_categorie);
        spinnerCategorieAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listeCategorie.setAdapter(spinnerCategorieAdapter);

        // On appelle la base de données pour remplir la liste d'articles
        // ArticleDao.getInstance((ActiviteEnAttenteAvecResultat) ArticleView.getInstance()).findAll();
        // Récupération de la liste des articles non filtrés
        temp_article = ArticleView.getInstance().liste;
        filtred_article = new ArrayList<>();
        spinnerArticleAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, filtred_article);
        spinnerArticleAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        listeArticle.setAdapter(spinnerArticleAdapter);
        listeArticle.setSelection(0 ,true);

        listeCategorie.setOnItemSelectedListener(new CategorieSpinnerClass());
        listeArticle.setOnItemSelectedListener(new ArticleSpinnerClass());

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
                                //dateDebutRequete=(year+":"+(month+1)+":"+dayOfMonth);

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
                                //dateFinRequete=(year+":"+(month+1)+":"+dayOfMonth);
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
                    dDeb = format.parse(dateDeb);
                    dFin = format.parse(dateF);
                    Log.i("date dDeb", valueOf(dDeb));
                    if (getDifferenceDays(dDeb, dFin) < 0) {
                        Log.i("pb", "date inf");
                        Toast.makeText(getApplicationContext(), "la date de fin et antérieur à la date de début", LENGTH_LONG).show();
                        dateDebut.setBackgroundColor(getColor(R.color.red));
                        dateFin.setBackgroundColor(getColor(R.color.red));
                        erreur = true;
                    } else {
                        dateDebut.setBackgroundColor(getColor(R.color.white));
                        dateFin.setBackgroundColor(getColor(R.color.white));
                        Log.i("dateDeb select",valueOf(dDeb));
                    }
                } catch (ParseException e) {
                    if (dateFin.getText().toString().isEmpty()) {
                        dateFin.setBackgroundColor(getColor(R.color.red));
                    } else {
                        dateFin.setBackgroundColor(getColor(R.color.red));
                    }
                    if (dateDebut.getText().toString().isEmpty()) {
                        dateDebut.setBackgroundColor(getColor(R.color.red));
                    } else {
                        dateDebut.setBackgroundColor(getColor(R.color.red));
                    }
                    e.printStackTrace();
                    erreur = true;
                }
                if (!erreur){

                    SimpleDateFormat formatRequete = new SimpleDateFormat("yyyy/MM/dd");
                    try {
                        //String dateDeb = dateDebut.getText().toString();
                        //String dateF = dateFin.getText().toString();
                        dateDebutRequete=formatRequete.parse(dateDeb);
                        dateFinRequete=formatRequete.parse(dateF);
                        Log.i("try", "ok");


                    }catch (ParseException e){
                        Log.i("catch","ok");
                    }
                Article articleRequete = (Article) listeArticle.getSelectedItem();
                Float pourcentRequete= Float.parseFloat(String.valueOf(et_pourcentage.getText()));
                Log.i("article select",valueOf(listeArticle.getSelectedItem()));
                Log.i("categorie select",valueOf(listeCategorie.getSelectedItem()));
                Log.i("date debut requete select", valueOf(dDeb));
                Log.i("date fin requete select", valueOf(dFin));
                Log.i("pourcentage select", valueOf(pourcentRequete));

                promotion = new Promotion(
                        articleRequete.getIdArticle(),
                        dDeb,
                        dFin,
                        pourcentRequete
                );
                    if (getIntent().getExtras() != null) {
                        Log.i("update","intent");
                        PromotionDao.getInstance((ActiviteEnAttenteAvecResultat) activite).update(promotion);
                    }
                    else{
                        PromotionDao.getInstance((ActiviteEnAttenteAvecResultat)activite).create(promotion);
                    }

            }
            }
        });
        if (this.getIntent().getExtras() != null) {
            promotion = (Promotion) this.getIntent().getSerializableExtra("nom");
            et_pourcentage.setText(valueOf(promotion.getPourcentage()));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            dateDebut.setText(format.format(promotion.getDateDebut()));
            dateFin.setText(format.format(promotion.getDateFin()));
            //ArticleDao.getInstance((ActiviteEnAttenteAvecResultat) activite).findCategorie(promotion.getIdArticle());
            // Initialisation du spinner à la valeur de l'article à modifier
            int countArticle = 0;
            int indexArticle = -1;
            int countCategorie =0 ;
            int indexCategorie = -1;
            /*for (Categorie i : temp_categorie) {
                if (i.getIdCateg()==article.getIdCategorie()){
                    index = count;
                    break;
                }
                count++;
            }
            listeCategorie.setSelection(index);
            count = 0;
            index = 0;
            for (Article i : temp_article) {
                if (i.getIdArticle()==promotion.getIdArticle()){
                    index = count;
                    break;
                }
                count++;
            }
            listeArticle.setSelection(index);*/

            for (Categorie i : temp_categorie){
                listeCategorie.setSelection(countCategorie);
                filtred_article.clear();
                for (Article k: temp_article) {
                    if(k.getIdCategorie()==i.getIdCateg()){
                        filtred_article.add(k);
                    }
                }
                for (Article j : filtred_article){
                    Log.i("liste article", valueOf(j.getNomArticle()));
                    if(j.getIdArticle()==promotion.getIdArticle()){
                        Log.i("if ", valueOf(j.getNomArticle()));
                        indexArticle=countArticle;
                        indexCategorie=countCategorie;
                        break;

                    }

                    countArticle++;
                }
                if(indexArticle>-1){
                    break;
                }
                countCategorie++;
            }
            listeArticle.setSelection(indexArticle);
            Log.i("index article", valueOf(indexArticle));
            listeCategorie.setSelection(indexCategorie);
            Log.i("index categorie", valueOf(indexCategorie));
            listeCategorie.setEnabled(false);
            listeArticle.setEnabled(false);
        }
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

        class CategorieSpinnerClass implements AdapterView.OnItemSelectedListener
        {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
            {
                listeArticle.setSelection(0);
                Categorie temp = (Categorie) parent.getSelectedItem();
                //Toast.makeText(v.getContext(), "Your choose : " + temp_categorie.get(position),Toast.LENGTH_SHORT).show();

                filtred_article.clear();
                for (Article i: temp_article) {
                    if(i.getIdCategorie()==temp.getIdCateg()){
                        filtred_article.add(i);
                    }
                }
                spinnerArticleAdapter.notifyDataSetChanged();
                listeArticle.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }

        class ArticleSpinnerClass implements AdapterView.OnItemSelectedListener
        {
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
            {
                //Toast.makeText(v.getContext(), "Your choose : " + temp_article.get(position),Toast.LENGTH_SHORT).show();
                Article temp = (Article) parent.getSelectedItem();
                Log.i("temp_article", temp.getNomArticle());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }
}
