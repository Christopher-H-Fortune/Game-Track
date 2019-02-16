package com.fullsail.christopherfortune.gametrack.AddMatchFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fullsail.christopherfortune.gametrack.MatchListFragment.MatchListFragment;
import com.fullsail.christopherfortune.gametrack.R;

public class AddMatchFragment extends Fragment {
    // String variable to reference the GamesListFragment when displaying
    public static final String TAG = "AddMatchFragment.TAG";

    // Variable to call the GamesListFragmentInterface methods
    public AddMatchFragmentInterface addMatchFragmentInterfaceListener;

    public interface AddMatchFragmentInterface{
        void saveMatch();
    }

    public static AddMatchFragment newInstance(){
        return new AddMatchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // If the context is an instance of MatchListFragmentInterface
        if(context instanceof AddMatchFragmentInterface){

            // Set the matchListFragmentInterfaceListener to the context as MatchListFragmentInterface
            addMatchFragmentInterfaceListener = (AddMatchFragmentInterface) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View addMatchFragmentView = inflater.inflate(R.layout.fragment_add_match, container, false);



        return addMatchFragmentView;
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
        inflater.inflate(R.menu.save_match_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // If the user presses add match button
        if(item.getItemId() == R.id.save_match_menu_button){

            addMatchFragmentInterfaceListener.saveMatch();
        }

        return true;
    }
}
