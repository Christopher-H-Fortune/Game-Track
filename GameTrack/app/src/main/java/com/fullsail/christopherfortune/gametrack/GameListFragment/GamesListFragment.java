package com.fullsail.christopherfortune.gametrack.GameListFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fullsail.christopherfortune.gametrack.R;

public class GamesListFragment extends ListFragment {

    // String variable to reference the GamesListFragment when displaying
    public static final String TAG = "GamesListFragment.TAG";

    public static GamesListFragment newInstance(){
        return new GamesListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Return the inflated fragment_games_list to display in the frame layout
        return inflater.inflate(R.layout.fragment_games_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set the fragment to have an options menu
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Display the games_list_menu to the fragment
        inflater.inflate(R.menu.games_list_menu, menu);
    }
}