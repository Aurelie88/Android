package com.iut.ecommerce.ecommerce.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.BoutiqueActivity;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.activity.InfosArticleActivity;
import com.iut.ecommerce.ecommerce.activity.InfosCategorieActivity;
import com.iut.ecommerce.ecommerce.adaptateur.ArticleAdaptateur;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

import java.util.ArrayList;

import static com.iut.ecommerce.ecommerce.BoutiqueActivity.boutiqueActivity;


public class ArticleView extends Fragment implements ActiviteEnAttenteAvecResultat,  AdapterView.OnItemClickListener, Dialog.OnClickListener{

    public ArrayList<Article> liste = new ArrayList<Article>();
    private ArticleAdaptateur adaptateur;
    private ListView listView;
    private Article article;
    private Article filteredArticle;

    private static ArticleView articleView;

    public static ArticleView getInstance(){
        if (articleView==null){
            articleView = new ArticleView();
        }
        return articleView;
    }

    public ArticleView() {

    }

    @Override
    public void onStart() {
        super.onStart();

        setFilteredArticle(null);

        // Définition du nom de l'activité
        getActivity().setTitle("Boutique");

        // Définition de la listView
        this.listView = getActivity().findViewById(R.id.articleListView);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Récupération des éléments de la liste
            if (CategorieView.getInstance().getFilteredCategorie() !=null) {
                //check
                int id_categorie = CategorieView.getInstance().getFilteredCategorie().getIdCateg();
                ArticleDao.getInstance(this).filter(id_categorie);
                Log.i("_av", "Filtrage des catégories");
            } else {
                setFilteredArticle(null);
                ArticleDao.getInstance(this).findAll();
                Log.i("_av", "Pas de filtrage suivant l'id catégorie");
            }
        } else {
            // Faire autre chose...
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.article_main, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void notifyRetourRequete(String resultat) {
        // Après création/modification/suppression, ajout d'éventuel message
        if ("supprimer".equals(resultat)) {
            Log.i("_S", "supprimer");

        } else if ("modifier".equals(resultat)) {
            Log.i("_M", "modifier");
            BoutiqueActivity.boutiqueActivity.setCurrentFragment();

        } else if ("creer".equals(resultat)){
            Log.i("_C", "creer");
            BoutiqueActivity.boutiqueActivity.setCurrentFragment();

        } else if ("nok".equals("nok")) {
            Log.i("_S", "erreur surpression");

        } else {
            Log.i("_S", "autre erreur");
        }
    }


    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {

        this.liste.clear();
        this.liste.addAll(liste);

        // Définition de l'adaptateur
        this.adaptateur = new ArticleAdaptateur(getContext(), liste);
        // Lien entre adaptateur et listview (remplissage de la liste
        listView.setAdapter(adaptateur);
        // Définition de l'action sur click sur un élément de la liste (texte ou bitmapArrayList catégorie)
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO : Action à réaliser lors d'un clique sur la liste ?
                Article item = (Article) adapterView.getItemAtPosition(i);
                Log.i("_cv", "Clique sur un article");
                setFilteredArticle(item);
                Log.i("_cv", getFilteredArticle().getNomArticle());
                boutiqueActivity.viewPager.setCurrentItem(2);
            }
        });

        // Définition de l'action sur click long sur un élément de la liste
        this.listView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO : Action à réaliser lors d'un clique sur la liste ?
                Article item = (Article) adapterView.getItemAtPosition(i);
                Log.i("_cv", "Clique sur un article");
                Intent intent = new Intent(getContext(), InfosArticleActivity.class);
                intent.putExtra("article", item);
                startActivity(intent);
                return false;
            }
        });

        ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();

    }
    //this.terminePatience();

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("adapter", String.valueOf(id));
        setArticle(this.liste.get(position));
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Article getFilteredArticle() {
        return filteredArticle;
    }

    public void setFilteredArticle(Article filteredArticle) {
        this.filteredArticle = filteredArticle;
    }
}
