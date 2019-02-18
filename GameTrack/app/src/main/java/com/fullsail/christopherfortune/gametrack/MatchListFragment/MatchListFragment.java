package com.fullsail.christopherfortune.gametrack.MatchListFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fullsail.christopherfortune.gametrack.R;

public class MatchListFragment extends ListFragment {

    // String variable to reference the GamesListFragment when displaying
    public static final String TAG = "MatchListFragment.TAG";

    // Variable to call the GamesListFragmentInterface methods
    public MatchListFragmentInterface matchListFragmentInterfaceListener;

    public interface MatchListFragmentInterface{
        void passListView(ListView matchListView);
        void addMatch();
    }

    public static MatchListFragment newInstance(){
        return new MatchListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // If the context is an instance of MatchListFragmentInterface
        if(context instanceof MatchListFragmentInterface){

            // Set the matchListFragmentInterfaceListener to the context as MatchListFragmentInterface
            matchListFragmentInterfaceListener = (MatchListFragmentInterface)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View matchListFragmentView = inflater.inflate(R.layout.fragment_match_list, container, false);

        ListView matchListView = matchListFragmentView.findViewById(android.R.id.list);

        matchListFragmentInterfaceListener.passListView(matchListView);

        matchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return matchListFragmentView;
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

        // Display the add match menu to the user
        inflater.inflate(R.menu.add_match_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // If the user presses add match button
        if(item.getItemId() == R.id.add_match_menu_button){

            // Call the addMatch interface method
            matchListFragmentInterfaceListener.addMatch();
        }

        return true;
    }
}
