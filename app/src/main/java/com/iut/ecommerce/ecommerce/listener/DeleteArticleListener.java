package com.iut.ecommerce.ecommerce.listener;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.iut.ecommerce.ecommerce.adaptateur.ArticleAdaptateur;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import static com.iut.ecommerce.ecommerce.Message.supprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

public class DeleteArticleListener implements DialogInterface.OnClickListener, View.OnClickListener{

    private Article article;
    private Context context;
    private int position;
    private ArticleAdaptateur articleAdaptateur;

    public DeleteArticleListener(Article article, Context context, ArticleAdaptateur articleAdaptateur) {
        this.article = article;
        this.context = context;
        this.articleAdaptateur = articleAdaptateur;
    }

    @Override
    public void onClick(View view) {
        this.position = (int) view.getTag();
        // On affiche la noite de dialogue pour la suppression du message
        supprimerAlertDialog(this.context,"Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné : ", this.article, this);

    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
        // Il n'existe pour chaque qu'un objet DAO et View!!!
        ArticleView articleView = ArticleView.getInstance();
        // On fait ici un appel à la base de données pour supprimer la catégorie
        ArticleDao.getInstance(articleView).delete(article);
        // On passe en paramètre la catégorie à supprimer pour la categorieView
        // On fait ici une suppression dans l'adpateur;
        Log.i("_cat", article.getNomArticle());




        Log.i("_cat", String.valueOf(position));
        //CategorieAdaptateur.liste.remove(2);
        //CategorieAdaptateur.getInstance(context).remove(categorie);

        Log.i("adapDCL", this.articleAdaptateur+"");
        Log.i("listeDCL_avantRemove", this.articleAdaptateur.liste.size() + "");

        this.articleAdaptateur.liste.remove(position);

        Log.i("listeDCL_apresRemove", this.articleAdaptateur.liste.size() + "");
        this.articleAdaptateur.notifyDataSetChanged();
        // On renvoie l'information que la suppression est terminée
        CategorieView.getInstance().notifyRetourRequete("Supprimer");

    }
}
