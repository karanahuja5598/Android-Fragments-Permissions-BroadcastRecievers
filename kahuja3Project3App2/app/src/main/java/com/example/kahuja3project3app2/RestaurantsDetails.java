package com.example.kahuja3project3app2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import android.app.Fragment;

public class RestaurantsDetails extends Fragment {
    public int currentIndex = -1;
    public int restaurantWebsiteLength = 0;

    public WebView webView = null;

    public void showDetails(int index) {
        if(index < 0 || index > restaurantWebsiteLength) {
            return;
        }
        currentIndex = index;
        webView.loadUrl(MainActivity.restaurantWebsites[currentIndex]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_restaurant,viewGroup,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView = getActivity().findViewById(R.id.restaurant_details);
        webView.setWebViewClient(new WebViewClient(){});

        restaurantWebsiteLength = MainActivity.restaurantWebsites.length;

        if(currentIndex != -1) {
            showDetails(currentIndex);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
