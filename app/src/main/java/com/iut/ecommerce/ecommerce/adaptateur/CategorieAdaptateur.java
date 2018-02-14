package com.iut.ecommerce.ecommerce.adaptateur;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.BoutiqueActivity;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.listener.DeleteCategorieListener;
import com.iut.ecommerce.ecommerce.listener.ModifyCategorieListener;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ImageFromURL;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Damien on 12/01/2018.
 */

public class CategorieAdaptateur extends ArrayAdapter<Categorie> {

    private Context context;
    private Categorie categorie;
    public Categorie currentCategorie;
    public ArrayList<Categorie> liste;
    private static CategorieAdaptateur categorieAdaptateur;

    private ImageView icone;
    private TextView tvNom;
    private ImageView modifier;
    private ImageView supprimer;

    public CategorieAdaptateur(Context context, ArrayList<Categorie> liste){
        // Context = l'activité parente
        super(context, R.layout.item_list_categorie, liste);
        this.context = context;
        this.liste = liste;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public static CategorieAdaptateur getInstance(Context context){
        if (categorieAdaptateur == null ){
            categorieAdaptateur = new CategorieAdaptateur(context, new ArrayList<Categorie>());
        }
        return categorieAdaptateur;
    }

    @Nullable
    @Override
    public Categorie getItem(int position) {
        this.currentCategorie = super.getItem(position);
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Récupération de l'objet indiqué par la position dans la liste
        setCategorie(getItem(position));

        // Si la convertView est null, on la crée
        if (convertView==null) {
            // On commence par crée une ligne de la listView avec inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_categorie, parent, false);

            // On enregitre les éléments dans le ViewHolder pour un accès ultérieur
            modifier = convertView.findViewById(R.id.cl_modifier);
            tvNom = convertView.findViewById(R.id.cl_nom);
            icone = convertView.findViewById(R.id.cl_visuel);
            supprimer = convertView.findViewById(R.id.cl_supprimer);
            //mViewHolder.modifier = (ImageView) convertView.findViewById(R.id.cl_modifier);

            // Définition d'un écouteur sur l'bitmapArrayList de suppression
            // On appelle le DeleteCategorieListener qui appellera la boite de dialogue pour la suppression
            // IMPORTANT : On obtient le bon context grâce à la vue view.getContext()
            supprimer.setOnClickListener(new DeleteCategorieListener(getCategorie(), supprimer.getContext(), categorieAdaptateur));

            // Définition d'un écouteur sur l'bitmapArrayList de modification
            // On appelle le ModifyCategorieListener qui appellera l'activité pour la modification
            // IMPORTANT : On obtient le bon context grâce à la vue view.getContext()
            modifier.setOnClickListener(new ModifyCategorieListener(getCategorie(), modifier.getContext()));
        }

        // On set le texte
        tvNom.setText(categorie.getNomCateg());

        // On set l'icone
        Picasso
                .with(context)
                .load("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + this.categorie.getVisuelCateg())
                .placeholder(R.drawable.ic_close) // can also be a drawable
                .error(R.drawable.ic_close) // will be displayed if the image cannot be loaded
                .into(icone);

        // Utilisation d'une fonction utilisatant un Thread
        /*        if (icone.getDrawable() == null) {
            ImageFromURL ifu = new ImageFromURL(getContext(), icone);
            ifu.execute("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + this.categorie.getVisuelCateg());
        }*/
        return convertView;
    }

    public void update(ArrayList<Categorie> liste) {
        this.liste.clear();
        this.liste.addAll(liste);
        notifyDataSetChanged();
    }
}
