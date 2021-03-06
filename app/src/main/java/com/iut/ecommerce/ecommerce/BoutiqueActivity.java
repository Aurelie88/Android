package com.iut.ecommerce.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iut.ecommerce.ecommerce.activity.AjouterArticleActivity;
import com.iut.ecommerce.ecommerce.activity.AjouterCategorieActivity;
import com.iut.ecommerce.ecommerce.activity.AjouterPromotionActivity;
import com.iut.ecommerce.ecommerce.adaptateur.ViewPagerAdapter;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;

import java.util.List;

public class BoutiqueActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Bundle savedInstanceState;

    // Sauvegarde du numéro de l'écran actuel du ViewPager
    private int currentScreen;

    // ViewPager
    public ViewPagerAdapter adapter;
    public ViewPager viewPager;

    // Onglets
    private CategorieView tabCategories;
    private ArticleView tabArticles;
    private PromotionView tabPromotions;
    public static BoutiqueActivity boutiqueActivity;


    private static final int MODIFICATION_ARTICLE=1;
    private static final int AJOUT_CATEGORIE=2;
    private static final int MODIFICATION_CATEGORIE=3;
    private static final int AJOUT_ARTICLE=4;
    private static final int AJOUT_PROMOTION=5;
    private static final int MODIFICATION_PROMOTION=6;

    private static Context appContext;

    // Fonction statique pour obtenir le context d'une application
    public static Context getAppContext(){
        return appContext;
    }


    @Override
    public void onStart(){
        super.onStart();
        this.viewPager = findViewById(R.id.viewPager);
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        for (int i=0; i<3; i++){
            tabLayout.getTabAt(i).select();
        }
        tabLayout.getTabAt(0).select();

        appContext = getApplicationContext();
        boutiqueActivity = this;

    }

    @Override
    public void onBackPressed() {
        // Gestion du bouton pour gérer k'ouverture et la ferneture du tiroir
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Création du menu et ajoute des actions si existante
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Sauvegarde du numéro de l'écran actif du viewPager
    public void setCurrentScreen(int currentScreen) {
        this.currentScreen = currentScreen;
    }

    // Connaître le numéro de l'écran actif du viewPager
    public int getCurrentScreen(){
        return this.currentScreen;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Récupère l'id de l'élément cliqué dans le menu du tiroir
        int id = item.getItemId();

        // Si l'id en cours et égale à l'id de la catégorie
        // on affiche la fragment catégorie
        if (id == R.id.nav_categ){
            this.viewPager.setCurrentItem(0);
        // Même principe pour article et promotion
        } else if (id == R.id.nav_article) {
            this.viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_promo) {
            this.viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_client) {
            Intent appelActivite = new Intent(this, VenteActivity.class);
            //startActivity(appelActivite);
        } else {
            Intent appelActivite = new Intent(this, VenteActivity.class);
            //startActivity(appelActivite);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // Prise en charge du Floating Action button
    // Suivant le fragment sur lequel on se trouve, on va lancer l'activité
    // pour ajouter une catégorie/un article ou une promotion
    public void ajouter(View view) {
        int id = this.viewPager.getCurrentItem();

        if (id==0){
            // Ajouter catégorie
            Intent appelActivite = new Intent(this.adapter.getItem(0).getContext(), AjouterCategorieActivity.class);
            startActivityForResult(appelActivite, AJOUT_CATEGORIE);
        } else if (id==1) {
            // Ajouter article
            Intent appelActivite = new Intent(this.adapter.getItem(1).getContext(), AjouterArticleActivity.class);
            startActivityForResult(appelActivite, AJOUT_ARTICLE);
        } else if (id==2) {
            // Ajouter promotion
            Intent appelActivite = new Intent(this.adapter.getItem(2).getContext(), AjouterPromotionActivity.class);
            startActivityForResult(appelActivite, AJOUT_PROMOTION);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Affichage de la fenêtre principale et de la toolbar
        setContentView(R.layout.activity_boutique);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Mise en place du tiroir latéral qui contient le menu
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.savedInstanceState = savedInstanceState;

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Ajout des fragments dans le viewPager
        // Lors du swipe, on bascule d'un fragment à l'autre
        if (savedInstanceState == null) {
            setCurrentScreen(0);
            this.tabCategories = CategorieView.getInstance();
            this.tabArticles = ArticleView.getInstance();
            this.tabPromotions = PromotionView.getInstance();
            adapter.addFragment(this.tabCategories, "Catégories");
            adapter.addFragment(this.tabArticles, "Articles");
            adapter.addFragment(this.tabPromotions, "Promotions");
        } else {
            //Restaurer les instance de fragments
            tabCategories = (CategorieView) getSupportFragmentManager().getFragment(savedInstanceState, "Catégories");
            tabArticles = (ArticleView) getSupportFragmentManager().getFragment(savedInstanceState, "Articles");
            tabPromotions = (PromotionView) getSupportFragmentManager().getFragment(savedInstanceState, "Promotions");
            adapter.addFragment(this.tabCategories, "Catégories");
            adapter.addFragment(this.tabArticles, "Articles");
            adapter.addFragment(this.tabPromotions, "Promotions");
        }

        // Mise en place du menu dans le tiroir latéral
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Mise en place des onglets
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Sauvegarde des instances de fragments
        getSupportFragmentManager().putFragment(outState, "Catégories", this.adapter.getItem(0));
        getSupportFragmentManager().putFragment(outState, "Articles", this.adapter.getItem(1));
        getSupportFragmentManager().putFragment(outState, "Promotions", this.adapter.getItem(2));
    }

    public void setCurrentFragment(){
        // on affiche la fragment par défaut
        if (getCurrentScreen() == R.id.nav_categ){
            this.viewPager.setCurrentItem(getCurrentScreen());
            // Même principe pour article et promotion
        } else if (getCurrentScreen() == R.id.nav_article) {
            this.viewPager.setCurrentItem(getCurrentScreen());
        } else if (getCurrentScreen() == R.id.nav_promo) {
            this.viewPager.setCurrentItem(getCurrentScreen());
        }
    }

}

