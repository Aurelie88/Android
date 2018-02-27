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

    public DeleteArticleListener(Article article, Context context) {
        this.article = article;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        ArticleView.getInstance().setPosition((int) view.getTag());
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
    }
}
