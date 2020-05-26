package com.example.kahuja3project3app2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import android.app.ListFragment;

public class AttractionsList extends ListFragment {
    private listListener listener = null;

    public static int currentIndex = -1;

    public interface listListener {
        void onListListener(int index);
    }

    @Override
    public void onAttach(Context mainActivity) {
        super.onAttach(mainActivity);

        try {
            listener = (listListener) mainActivity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(mainActivity.toString()
                    + " must implement the List Listener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return super.onCreateView(inflater,viewGroup,savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item_attraction,Activity2.attractionNames));
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
    public void onListItemClick(ListView l, View v, int position, long id) {
        currentIndex = position;
        listener.onListListener(position);
        l.setItemChecked(currentIndex, true);
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
