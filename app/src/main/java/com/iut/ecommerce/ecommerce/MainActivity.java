package com.iut.ecommerce.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.adaptateur.ViewPagerAdapter;
import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.ClientView;
import com.iut.ecommerce.ecommerce.fragment.CommandeView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView maListe;
    private int currentScreen;
    private ViewPagerAdapter adapter;
    private ViewPager viewPager;

    private static final int AJOUT_ARTICLE=0;
    private static final int MODIFICATION_ARTICLE=1;
    private static final int AJOUT_CATEGORIE=2;
    private static final int MODIFICATION_CATEGORIE=3;

    public void setCurrentScreen(int currentScreen) {
        this.currentScreen = currentScreen;
    }

    public int getCurrentScreen(){
        return this.currentScreen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }


    private  void  setupViewPager (ViewPager viewPager){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategorieView(), "Catégorie");
        adapter.addFragment(new ArticleView(), "Articles");
        adapter.addFragment(new PromotionView(), "Promotions");
        this.viewPager = viewPager;
        this.viewPager.setAdapter(adapter);

    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_categ){
            this.viewPager.setCurrentItem(0);
            this.viewPager.getCurrentItem();
        } else if (id == R.id.nav_article) {
            this.viewPager.setCurrentItem(1);
            this.viewPager.getCurrentItem();

        } else if (id == R.id.nav_promo) {
            this.viewPager.setCurrentItem(2);
            this.viewPager.getCurrentItem();

        } else if (id == R.id.nav_client) {
            fragment = new ClientView();

        } else if (id == R.id.nav_commmande) {
            fragment = new CommandeView();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public void ajouter(View view) {
        int id = this.viewPager.getCurrentItem();

        if (id==0){
            // Ajouter catégorie
            Intent appelActivite = new Intent(this, AjouterCategorieActivity.class);
            startActivityForResult(appelActivite, AJOUT_CATEGORIE);

        } else if (id==1) {
            // Ajouter article
            Snackbar.make(view, "Ajouter", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            Intent appelActivite = new Intent(this, AjouterArticleActivity.class);
            //appelActivite.putExtra("devise", this.devise);
            startActivityForResult(appelActivite, AJOUT_ARTICLE);

        } else if (id==2) {
            // Ajouter promotion

        }
    }
}

