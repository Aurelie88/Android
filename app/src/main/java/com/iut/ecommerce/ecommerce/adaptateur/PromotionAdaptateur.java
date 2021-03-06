package com.iut.ecommerce.ecommerce.adaptateur;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.listener.DeleteArticleListener;
import com.iut.ecommerce.ecommerce.listener.DeletePromotionListener;
import com.iut.ecommerce.ecommerce.listener.ModifyArticleListener;
import com.iut.ecommerce.ecommerce.listener.ModifyPromotionListener;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.modele.Promotion;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Damien on 12/01/2018.
 */

public class PromotionAdaptateur extends ArrayAdapter<Promotion> {

    private Context context;
    private Promotion promotion;
    public ArrayList<Promotion> liste;

    //private ImageView icone;
    private TextView tvNom;
    private TextView tvArticle;
    private TextView tvdateDebut;
    private TextView tvdateFin;
    private ImageView modifier;
    private ImageView supprimer;

    public PromotionAdaptateur(Context context, ArrayList<Promotion> liste){
        // Context = l'activité parente
        super(context, R.layout.item_list_promotion, liste);
        this.context = context;
        this.liste = liste;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Récupération de l'objet indiqué par la position dans la liste
        setPromotion(getItem(position));

        // Si la convertView est null, on la crée
            // On commence par crée une ligne de la listView avec inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_promotion, parent, false);

            // On enregitre les éléments dans le ViewHolder pour un accès ultérieur
            modifier = convertView.findViewById(R.id.cl_modifier);
            tvNom = convertView.findViewById(R.id.cl_nom);
            tvArticle = convertView.findViewById(R.id.cl_article);
            tvdateDebut = convertView.findViewById(R.id.cl_date_debut);
            tvdateFin = convertView.findViewById(R.id.cl_date_fin);
            //icone = convertView.findViewById(R.id.cl_visuel);
            supprimer = convertView.findViewById(R.id.cl_supprimer);
            supprimer.setTag(position);
            Log.i("postion", position + " " + this.getItem(position));
            //mViewHolder.modifier = (ImageView) convertView.findViewById(R.id.cl_modifier);

            // Définition d'un écouteur sur l'bitmapArrayList de suppression
            // On appelle le DeleteArticleListener qui appellera la boite de dialogue pour la suppression
            // IMPORTANT : On obtient le bon context grâce à getContext()
            supprimer.setOnClickListener(new DeletePromotionListener(getPromotion(), supprimer.getContext(), this));

            // Définition d'un écouteur sur l'bitmapArrayList de modification
            // On appelle le ModifyArticleListener qui appellera l'activité pour la modification
            // IMPORTANT : On obtient le bon context grâce à getContext()
            modifier.setOnClickListener(new ModifyPromotionListener(getPromotion(), modifier.getContext()));

        // On set le texte
        tvNom.setText(promotion.toString());
        tvArticle.setText("");
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        tvdateDebut.setText(df.format(promotion.getDateDebut()));
        tvdateFin.setText(df.format(promotion.getDateFin()));

        // Evitons des problèmes d'affichage :p
        /*
        // Pour retrouver la catégorie parente, on récupère la liste des catégories
        ArrayList<Article> temp = ArticleView.getInstance().liste;
        for (Article i: temp){
            if(i.getIdArticle()==promotion.getIdArticle()){
                tvArticle.setText(i.getNomArticle());
            }
        }
        */

        return convertView;
    }
}
