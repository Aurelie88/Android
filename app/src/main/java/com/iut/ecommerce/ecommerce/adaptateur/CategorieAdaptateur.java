package com.iut.ecommerce.ecommerce.adaptateur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.utils.ImageFromURL;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;

/**
 * Created by Damien on 12/01/2018.
 */

public class CategorieAdaptateur extends ArrayAdapter<Categorie> {

    private Context context;
    private Categorie categorie;

    public CategorieAdaptateur(Context context, ArrayList<Categorie> liste){
        // Context = l'activité parente
        super(context, R.layout.item_list_categorie, liste);
        this.context = context;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Récupération de l'objet indiqué par la position dans la liste
        this.categorie = getItem(position);

        // Création d'un viewHolder. Conserve les informations déjà chargées
        // Utile dans le cas d'une longue liste
        ViewHolder mViewHolder = new ViewHolder();

        // Si la convertView est null, on la crée
        if (convertView==null){
            // On commence par crée une ligne de la listView avec inflate
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_list_categorie, parent, false);

            // On enregitre les éléments dans le ViewHolder pour un accès ultérieur
            mViewHolder.tvNom = convertView.findViewById(R.id.cl_nom);
            mViewHolder.icone = (ImageView) convertView.findViewById(R.id.cl_visuel);
            mViewHolder.supprimer = (ImageView) convertView.findViewById(R.id.cl_supprimer);
            mViewHolder.modifier = (ImageView) convertView.findViewById(R.id.cl_modifier);

            // Définition d'un écouteur sur l'image de suppression
            mViewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Supprimer", "Appui sur le bouton suppression");
                    SupprimerAlertDialog("Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné ?");
                }
            });

            // Définition d'un écouteur sur l'image de modification
            mViewHolder.modifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("Modifier", "Appui sur le bouton modifier");
                    // TODO Ajouter l'intent vers l'activité ModifierCategorieActivity
                    //Intent intent = new Intent(getContext(), ModifierCategorieActivity.class);
                    //intent.putExtra("nom", categorie.getNomCateg());
                    //intent.putExtra("visuel", categorie.getVisuelCateg());
                    //startActivityForResult(intent, 1);
                }
            });

            // On ajoute un tag à la convertView
            convertView.setTag(mViewHolder);

        } else {
            // La convertView n'est pas null, dans ce cas, on récupère le viewHolder
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        // On set le texte
        mViewHolder.tvNom.setText(categorie.getNomCateg());

        // On set l'image
        if (mViewHolder.icone.getDrawable() == null) {
            ImageFromURL ifu = new ImageFromURL(getContext(), mViewHolder.icone);
            ifu.execute("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + this.categorie.getVisuelCateg());
        }
        return convertView;
    }

    // Boîte de dialogue d'alerte pour la suppression
    // Si implémentation dans une autre classe, penser à ajouter le context en paramètre
    private void SupprimerAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i("Supprimer", "La suppression est validée");
                // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
                // Il n'existe pour chaque qu'un objet DAO et View!!!
                // On appelle ensuite la catégorie à supprimer (Dont on ne sait pas trop
                // comment on réussi à le récupérer ?!?
                CategorieDao.getInstance(CategorieView.getInstance()).delete(categorie);


            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // On ne fait rien
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // ViewHolder, bonnes pratiques pour la gestion d'une longue liste
    // Pour simplifier, pas de getters/setters
    static class ViewHolder{
        ImageView icone;
        TextView tvNom;
        ImageView supprimer;
        ImageView modifier;
    }

}
