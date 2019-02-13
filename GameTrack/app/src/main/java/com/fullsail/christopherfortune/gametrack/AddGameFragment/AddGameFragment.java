package com.fullsail.christopherfortune.gametrack.AddGameFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fullsail.christopherfortune.gametrack.R;

public class AddGameFragment extends Fragment {

    // String variable to reference the AddGameFragment when displaying
    public static final String TAG = "AddGameFragment.TAG";

    // Variable to call the AddGameFragmentInterface methods
    public AddGameFragmentInterface addGameFragmentInterfaceListener;

    private EditText gameTitleEditText;
    private EditText gameDeveloperEditText;
    private EditText gamePublisherEditText;
    private EditText gameReleaseYearEditText;
    private EditText gameLauncherEditText;

    public interface AddGameFragmentInterface{
        void saveGame(EditText gameTitleEditText,
                      EditText gameDeveloperEditText,
                      EditText gamePublisherEditText,
                      EditText gameReleaseYearEditText,
                      EditText gameLauncherEditText);
    }

    public static AddGameFragment newInstance(){
        return new AddGameFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View addGameFragmentView = inflater.inflate(R.layout.fragment_add_game, container, false);

        gameTitleEditText = addGameFragmentView.findViewById(R.id.game_title_edit_text);
        gameDeveloperEditText = addGameFragmentView.findViewById(R.id.game_dev_edit_text);
        gamePublisherEditText = addGameFragmentView.findViewById(R.id.game_publisher_edit_text);
        gameReleaseYearEditText = addGameFragmentView.findViewById(R.id.release_year_edit_text);
        gameLauncherEditText = addGameFragmentView.findViewById(R.id.game_launcher_edit_text);

        return addGameFragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // If the context is an instance of AddGameFragmentInterface
        if(context instanceof AddGameFragmentInterface){

            // Set the addGameFragmentInterfaceListener to the context as AddGameFragmentInterface
            addGameFragmentInterfaceListener = (AddGameFragmentInterface)context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set the fragment to display an options menu
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Display the add_game_menu
        inflater.inflate(R.menu.add_game_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // If the user pressed the save_game_menu_button
        if(item.getItemId() == R.id.save_game_menu_button){

            // Call the saveGame interface method
            addGameFragmentInterfaceListener.saveGame(gameTitleEditText,
                    gameDeveloperEditText,
                    gamePublisherEditText,
                    gameReleaseYearEditText,
                    gameLauncherEditText);
        }

        return true;
    }
}
