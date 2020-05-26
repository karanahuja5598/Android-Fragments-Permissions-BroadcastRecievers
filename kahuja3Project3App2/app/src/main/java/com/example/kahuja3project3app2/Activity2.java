package com.example.kahuja3project3app2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.FragmentTransaction;

public class Activity2 extends AppCompatActivity implements AttractionsList.listListener {

    public static final int Attraction_Parent = LinearLayout.LayoutParams.MATCH_PARENT;

    private AttractionsList attractionsListFragment;

    private AttractionsDetails attractionsDetailsFragment;

    private FragmentManager fragmentManager;

    private FrameLayout attractionNamesLayout;
    private FrameLayout attractionWebsitesLayout;

    static String[] attractionNames;
    static String[] attractionWebsites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        attractionNames = getResources().getStringArray(R.array.attractionNames);
        attractionWebsites = getResources().getStringArray(R.array.attractionWebsites);

        attractionNamesLayout = findViewById(R.id.attraction_names);
        attractionWebsitesLayout = findViewById(R.id.attraction_websites);

        fragmentManager = getFragmentManager();

        if(savedInstanceState == null) {
            attractionsListFragment = new AttractionsList();
            attractionsDetailsFragment = new AttractionsDetails();

            fragmentManager.beginTransaction().replace(R.id.attraction_names,attractionsListFragment,"LIST_FRAGMENT").commit();
        } else {
            attractionsListFragment = (AttractionsList) getFragmentManager().findFragmentByTag("LIST_FRAGMENT");

            if(attractionsListFragment == null)
                attractionsListFragment = new AttractionsList();


            attractionsDetailsFragment = (AttractionsDetails) getFragmentManager().findFragmentByTag("WEBSITE_FRAGMENT");

            if(attractionsDetailsFragment == null)
                attractionsDetailsFragment = new AttractionsDetails();

        }

        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setPortrait();
        } else if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setLandscape();
        }

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Configuration configuration1 = getResources().getConfiguration();
                if(configuration1.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setPortrait();
                } else if (configuration1.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setLandscape();
                }
            }
        });

    }

    public void onListListener(int index) {
        if(!attractionsDetailsFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.attraction_websites,attractionsDetailsFragment,"WEBSITE_FRAGMENT").addToBackStack(null);
            fragmentTransaction.commit();

            getFragmentManager().executePendingTransactions();

            attractionsDetailsFragment.showDetails(index);
        } else {
            attractionsDetailsFragment.showDetails(index);
        }
    }

    public void setPortrait() {
        if(!attractionsDetailsFragment.isAdded()){
            attractionNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Attraction_Parent,Attraction_Parent
            ));
            attractionWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Attraction_Parent
            ));
        }
        else{
            attractionNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,Attraction_Parent
            ));
            attractionWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Attraction_Parent,Attraction_Parent
            ));
        }
    }

    public void setLandscape() {
        if(!attractionsDetailsFragment.isAdded()){
            attractionNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Attraction_Parent,Attraction_Parent
            ));
            attractionWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Attraction_Parent
            ));
        }
        else{
            attractionNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,Attraction_Parent, 1f
            ));
            attractionWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Attraction_Parent,2f
            ));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.attractions_option:
                Toast.makeText(this,"This is the Attractions list, please choose another list", Toast.LENGTH_SHORT).show();
                break;

            case R.id.restaurants_option:
                Intent openRestaurantsActivity = new Intent(this, MainActivity.class);
                startActivity(openRestaurantsActivity);
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
