package com.iut.ecommerce.ecommerce.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.iut.ecommerce.ecommerce.activity.ModifierCategorieActivity;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;

/**
 * Created by Damien on 23/02/2018.
 */

public class ModifyArticleListener implements View.OnClickListener {
    private Article article;
    private Context context;

    private static final int MODIFICATION_ARTICLE=1;

    public ModifyArticleListener(Article article, Context context) {
        // NOTA : Le context est celui de la vue (widget)! D'où le view.getContext() de
        // la classe appelante.
        this.article = article;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.context, ModifierCategorieActivity.class);
        intent.putExtra("nom", this.article.getNomArticle());
        intent.putExtra("visuel", this.article.getVisuelArticle());
        // Le cast permet d'accéder à la méthode startActivityForResult
        ((Activity) this.context).startActivityForResult(intent, MODIFICATION_ARTICLE);
    }
}
