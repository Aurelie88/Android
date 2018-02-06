package com.iut.ecommerce.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iut.ecommerce.ecommerce.activity.AjouterCategorieActivity;
import com.iut.ecommerce.ecommerce.adaptateur.ViewPagerAdapter;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;

public class BoutiqueActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Sauvegarde du numéro de l'écran actuel du ViewPager
    private int currentScreen;

    // ViewPager
    private ViewPagerAdapter adapter;
    private ViewPager viewPager;

    private static final int AJOUT_ARTICLE=0;
    private static final int MODIFICATION_ARTICLE=1;
    private static final int AJOUT_CATEGORIE=2;
    private static final int MODIFICATION_CATEGORIE=3;

    private static Context appContext;

    /*  @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);

          // Affichage de la fenêtre principale et de la toolbar
          setContentView(R.layout.activity_main);
          Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
          setSupportActionBar(toolbar);


          // Mise en place du tiroir latéral qui contient le menu
          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                  this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
          drawer.addDrawerListener(toggle);
          toggle.syncState();

          // Mise en place du menu dans le tiroir latéral
          NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
          navigationView.setNavigationItemSelectedListener(this);

          // Mise en place du ViewPager
          ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
          // setOffscreenPageLimit permet de conserver l'état des différents fragements
          // durant le swipe. On peut ici swiper entre trois écrans sans risque d'effacer le fragment
          viewPager.setOffscreenPageLimit(2);
          this.setupViewPager(viewPager);


          // Mise en place des onglets
          TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
          tabLayout.setupWithViewPager(viewPager);


      }
  */
    // Ajout des fragments dans le viewPager
    // Lors du swipe, on bascule d'un fragment à l'autre
    private void setupViewPager (ViewPager viewPager){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategorieView(), "Catégorie");
        adapter.addFragment(new ArticleView(), "Articles");
        adapter.addFragment(new PromotionView(), "Promotions");
        this.viewPager = viewPager;
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setAdapter(adapter);

    }


    public static Context getAppContext(){
        return appContext;
    }

    @Override
    public void onStart(){
        super.onStart();
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.viewPager.setOffscreenPageLimit(2);
        this.viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        for (int i=0; i<3; i++){
            tabLayout.getTabAt(i).select();
        }
        tabLayout.getTabAt(0).select();

        appContext = getApplicationContext();
    }

    @Override
    public void onBackPressed() {
        // Gestion du bouton pour gérer k'ouverture et la ferneture du tiroir
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Création du menu et ajoute des actions si existante
        getMenuInflater().inflate(R.menu.main, menu);
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
            Intent appelActivite = new Intent(this, AjouterCategorieActivity.class);
            startActivityForResult(appelActivite, AJOUT_CATEGORIE);

        } else if (id==1) {
            // Ajouter article
            Message.afficheMessageSnack(view, "Ajouter article", Snackbar.LENGTH_LONG);

        } else if (id==2) {
            // Ajouter promotion
            Message.afficheMessageSnack(view, "Ajouter promotion", Snackbar.LENGTH_LONG);
        }
    }

 /*   private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;*/

    private CategorieView tabCategories;
    private ArticleView tabArticles;
    private PromotionView tabPromotions;

    private Bundle savedInstanceState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Affichage de la fenêtre principale et de la toolbar
        setContentView(R.layout.activity_boutique);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Mise en place du tiroir latéral qui contient le menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        this.savedInstanceState = savedInstanceState;

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (savedInstanceState == null) {
            this.tabCategories = new CategorieView();
            this.tabArticles = new ArticleView();
            this.tabPromotions = new PromotionView();
            adapter.addFragment(this.tabCategories, "Catégories");
            adapter.addFragment(this.tabArticles, "Articles");
            adapter.addFragment(this.tabPromotions, "Promotions");
            Log.e("oC", getFragmentTag(2));
        } else {
            Integer count = savedInstanceState.getInt("tabsCount");
            String[] titles = savedInstanceState.getStringArray("titles");
            for (int i = 0; i < count; i++) {
                adapter.addFragment(getFragment(i), titles[i]);
            }
        }
        // Mise en place du menu dans le tiroir latéral
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Mise en place des onglets
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.savedInstanceState = savedInstanceState;

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (savedInstanceState == null) {
            this.tabCategories = new CategorieFragment();
            this.tabArticles = new ArticleFragment();
            this.tabPromotions = new PromotionFragment();
            adapter.addFragment(this.tabCategories, "Catégories");
            adapter.addFragment(this.tabArticles, "Articles");
            adapter.addFragment(this.tabPromotions, "Promotions");
            Log.e("oC", getFragmentTag(2));
        } else {
            Integer count = savedInstanceState.getInt("tabsCount");
            String[] titles = savedInstanceState.getStringArray("titles");
            for (int i = 0; i < count; i++) {
                adapter.addFragment(getFragment(i), titles[i]);
            }
        }
    }
*/
    private Fragment getFragment(int position){

        if (this.savedInstanceState == null) {
            Log.e("gF null " , adapter.getItem(position)+"");
            return adapter.getItem(position);
        } else {
            Log.e("gF not null " , getSupportFragmentManager().findFragmentByTag(getFragmentTag(position))+"");
            if (getSupportFragmentManager().findFragmentByTag(getFragmentTag(position))==null) {
                Log.e("gF sf null " , adapter.getItem(position)+"");
                return adapter.getItem(position);
            } else
                return getSupportFragmentManager().findFragmentByTag(getFragmentTag(position));
        }
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + R.id.viewPager + ":" + position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tabsCount",      adapter.getCount());
        outState.putStringArray("titles", adapter.getPageTitles().toArray(new String[0]));
    }


}

