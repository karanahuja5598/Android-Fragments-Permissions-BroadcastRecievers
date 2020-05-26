package com.example.kahuja3project3app2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements RestaurantsList.listListener {
    private BroadcastReceiver receiver;
    Intent openRestaurants;
    Intent openAttractions;

    private static final String permission = "edu.uic.cs478.sp2020.project3";
    public static final int Restaurant_Parent = LinearLayout.LayoutParams.MATCH_PARENT;

    private RestaurantsList restaurantsListFragment;

    private RestaurantsDetails restaurantDetailsFragment;

    private FragmentManager fragmentManager;

    private FrameLayout restaurantNamesLayout;
    private FrameLayout restaurantWebsitesLayout;

    static String[] restaurantNames;
    static String[] restaurantWebsites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeReceiver();


        restaurantNames = getResources().getStringArray(R.array.restaurantNames);
        restaurantWebsites = getResources().getStringArray(R.array.restaurantWebsites);

        restaurantNamesLayout = findViewById(R.id.restaurant_names);
        restaurantWebsitesLayout = findViewById(R.id.restaurant_websites);

        fragmentManager = getFragmentManager();

        if(savedInstanceState == null) {
            restaurantsListFragment = new RestaurantsList();
            restaurantDetailsFragment = new RestaurantsDetails();

            fragmentManager.beginTransaction().replace(R.id.restaurant_names,restaurantsListFragment,"LIST_FRAGMENT").commit();
        } else {
            restaurantsListFragment = (RestaurantsList) getFragmentManager().findFragmentByTag("LIST_FRAGMENT");

            if(restaurantsListFragment == null)
                restaurantsListFragment = new RestaurantsList();


            restaurantDetailsFragment = (RestaurantsDetails) getFragmentManager().findFragmentByTag("WEBSITE_FRAGMENT");

            if(restaurantDetailsFragment == null)
                restaurantDetailsFragment = new RestaurantsDetails();

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
        if(!restaurantDetailsFragment.isAdded()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.restaurant_websites,restaurantDetailsFragment,"WEBSITE_FRAGMENT").addToBackStack(null);
            fragmentTransaction.commit();

            getFragmentManager().executePendingTransactions();

            restaurantDetailsFragment.showDetails(index);
        } else {
            restaurantDetailsFragment.showDetails(index);
        }
    }

    public void setPortrait() {
        if(!restaurantDetailsFragment.isAdded()){
            restaurantNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Restaurant_Parent,Restaurant_Parent
            ));
            restaurantWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Restaurant_Parent
            ));
        }
        else{
            restaurantNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,Restaurant_Parent
            ));
            restaurantWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Restaurant_Parent,Restaurant_Parent
            ));
        }
    }

    public void setLandscape() {
        if(!restaurantDetailsFragment.isAdded()){
            restaurantNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    Restaurant_Parent,Restaurant_Parent
            ));
            restaurantWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Restaurant_Parent
            ));
        }
        else{
            restaurantNamesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0,Restaurant_Parent, 1f
            ));
            restaurantWebsitesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    0, Restaurant_Parent,2f
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
                Intent openAttractionsActivity = new Intent(this, Activity2.class);
                startActivity(openAttractionsActivity);
                break;
            case R.id.restaurants_option:
                Toast.makeText(this,"This is the Restaurants list, please choose another list", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (code == 1) {
                    initializeReceiver();
                }
            }
            else {
                Toast.makeText(this, "Permission is not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initializeReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("com.example.kahuja3project3app1.Restaurants".equals(action)) {
                    String choice1 = "Restaurants";
                    Toast.makeText(context, choice1 + " has been chosen", Toast.LENGTH_LONG).show();

                    openRestaurants = new Intent(context, MainActivity.class);

                    context.startActivity(openRestaurants);
                    finish();
                } else if ("com.example.kahuja3project3app1.Attractions".equals(action)) {
                    String choice2 = "Attractions";
                    Toast.makeText(context, choice2 + " has been chosen", Toast.LENGTH_LONG).show();

                    openAttractions = new Intent(context, Activity2.class);
                    context.startActivity(openAttractions);
                }
            }
        };

        IntentFilter filter = new IntentFilter();

        filter.addAction("com.example.kahuja3project3app1.Attractions");
        filter.addAction("com.example.kahuja3project3app1.Restaurants");

        registerReceiver(receiver, filter, "edu.uic.cs478.sp2020.project3", null);
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
        unregisterReceiver(receiver);
    }

}
