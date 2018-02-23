package com.iut.ecommerce.ecommerce.listener;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.iut.ecommerce.ecommerce.adaptateur.ArticleAdaptateur;
import com.iut.ecommerce.ecommerce.adaptateur.PromotionAdaptateur;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.dao.PromotionDao;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Promotion;

import static com.iut.ecommerce.ecommerce.Message.supprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

public class DeletePromotionListener implements DialogInterface.OnClickListener, View.OnClickListener{

    private Promotion promotion;
    private Context context;
    private int position;
    private PromotionAdaptateur promotionAdaptateur;

    public DeletePromotionListener(Promotion promotion, Context context, PromotionAdaptateur promotionAdaptateur) {
        this.promotion = promotion;
        this.context = context;
        this.promotionAdaptateur = promotionAdaptateur;
    }

    @Override
    public void onClick(View view) {
        this.position = (int) view.getTag();
        // On affiche la noite de dialogue pour la suppression du message
        supprimerAlertDialog(this.context,"Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné : ", this.promotion, this);

    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
        // Il n'existe pour chaque qu'un objet DAO et View!!!
        PromotionView promotionView = PromotionView.getInstance();
        // On fait ici un appel à la base de données pour supprimer la catégorie
        PromotionDao.getInstance(promotionView).delete(promotion);
        // On passe en paramètre la catégorie à supprimer pour la categorieView
        // On fait ici une suppression dans l'adpateur;
        Log.i("_cat", promotion.toString());




        Log.i("_cat", String.valueOf(position));
        //CategorieAdaptateur.liste.remove(2);
        //CategorieAdaptateur.getInstance(context).remove(categorie);

        Log.i("adapDCL", this.promotionAdaptateur+"");
        Log.i("listeDCL_avantRemove", this.promotionAdaptateur.liste.size() + "");

        this.promotionAdaptateur.liste.remove(position);

        Log.i("listeDCL_apresRemove", this.promotionAdaptateur.liste.size() + "");
        this.promotionAdaptateur.notifyDataSetChanged();
        // On renvoie l'information que la suppression est terminée
        CategorieView.getInstance().notifyRetourRequete("Supprimer");

    }
}
