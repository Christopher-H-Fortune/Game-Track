package com.fullsail.christopherfortune.gametrack.GameListFragment;

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

public class GamesListFragment extends ListFragment {

    // String variable to reference the GamesListFragment when displaying
    public static final String TAG = "GamesListFragment.TAG";

    // Variable to call the GamesListFragmentInterface methods
    public GamesListFragmentInterface gamesListFragmentInterfaceListener;

    public interface GamesListFragmentInterface{
        void addGame();
        void passListView(ListView gamesListView);
        void viewGame(int gameChosen);
    }

    public static GamesListFragment newInstance(){
        return new GamesListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // If the context is an instance of GamesListFragmentInterface
        if(context instanceof GamesListFragmentInterface){

            // Set the gamesListFragmentInterfaceListener to the context as GamesListFragmentInterface
            gamesListFragmentInterfaceListener = (GamesListFragmentInterface)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Return the fragment_games_list to display in the frame layout
       return inflater.inflate(R.layout.fragment_games_list, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set the fragment to have an options menu
        setHasOptionsMenu(true);

        // If you can get the view
        if (getView() != null){

            // Get the list View to display the data
            ListView listView = getView().findViewById(android.R.id.list);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Call the view game method passing the position chosen
                    gamesListFragmentInterfaceListener.viewGame(position);
                }
            });

            // Call the updateListView interface method passing the listView declared above
            gamesListFragmentInterfaceListener.passListView(listView);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Display the games_list_menu to the fragment
        inflater.inflate(R.menu.games_list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // If the user pressed the add game button
        if(item.getItemId() == R.id.add_game_menu_button){

            // Call the addGame interface method
            gamesListFragmentInterfaceListener.addGame();
        }

        return true;
    }
}
