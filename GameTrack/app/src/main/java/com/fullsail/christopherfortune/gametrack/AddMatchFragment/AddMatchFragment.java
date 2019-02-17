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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fullsail.christopherfortune.gametrack.R;

public class AddMatchFragment extends Fragment {
    // String variable to reference the GamesListFragment when displaying
    public static final String TAG = "AddMatchFragment.TAG";

    // Variable to call the GamesListFragmentInterface methods
    public AddMatchFragmentInterface addMatchFragmentInterfaceListener;

    private RadioButton winRadioButton;
    private RadioButton loseRadioButton;
    private EditText mapNameEditText;
    private EditText matchLengthEditText;
    private EditText killsEditText;
    private EditText deathsEditText;
    private EditText mainWeaponEditText;
    private EditText secondaryWeaponEditText;
    private EditText grenadesEditText;
    private EditText matchScoreEditText;
    private EditText matchAssistsEditText;
    private EditText matchNotesEditText;

    public interface AddMatchFragmentInterface{
        void saveMatch(RadioButton winRadioButton,
                       RadioButton loseRadioButton,
                       EditText mapNameEditText,
                       EditText matchLengthEditText,
                       EditText killsEditText,
                       EditText deathsEditText,
                       EditText mainWeaponEditText,
                       EditText secondaryWeaponEditText,
                       EditText grenadesEditText,
                       EditText matchScoreEditText,
                       EditText matchAssistEditText,
                       EditText matchNotesEditText);
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

        // Get the Edit text and Radio Button fields to get the data the user entered
       winRadioButton = addMatchFragmentView.findViewById(R.id.win_radio_button);
       loseRadioButton = addMatchFragmentView.findViewById(R.id.lose_radio_button);
       mapNameEditText = addMatchFragmentView.findViewById(R.id.map_name_edit_text);
       matchLengthEditText = addMatchFragmentView.findViewById(R.id.match_length_edit_text);
       killsEditText = addMatchFragmentView.findViewById(R.id.kills_edit_text);
       deathsEditText = addMatchFragmentView.findViewById(R.id.deaths_edit_text);
       mainWeaponEditText = addMatchFragmentView.findViewById(R.id.main_weapon_edit_text);
       secondaryWeaponEditText = addMatchFragmentView.findViewById(R.id.secondary_weapon_edit_text);
       grenadesEditText = addMatchFragmentView.findViewById(R.id.grenades_used_edit_text);
       matchScoreEditText = addMatchFragmentView.findViewById(R.id.match_score_edit_text);
       matchAssistsEditText = addMatchFragmentView.findViewById(R.id.match_assist_edit_text);
       matchNotesEditText = addMatchFragmentView.findViewById(R.id.match_notes_edit_text);

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

            // Make sure the user entered data into the text fields
            if(mapNameEditText.getText().toString().trim().isEmpty() ||
                    matchLengthEditText.getText().toString().trim().isEmpty() ||
                    killsEditText.getText().toString().trim().isEmpty() ||
                    deathsEditText.getText().toString().trim().isEmpty() ||
                    mainWeaponEditText.getText().toString().trim().isEmpty() ||
                    secondaryWeaponEditText.getText().toString().trim().isEmpty() ||
                    grenadesEditText.getText().toString().trim().isEmpty() ||
                    matchScoreEditText.getText().toString().trim().isEmpty() ||
                    matchAssistsEditText.getText().toString().trim().isEmpty() ||
                    matchNotesEditText.getText().toString().trim().isEmpty()){

                // Ask the user to fill out all the data in the edit text fields
                Toast.makeText(getContext(), "Please Fill out all the text fields.", Toast.LENGTH_SHORT).show();

            // If no radio button is checked
            } else if (!winRadioButton.isChecked() && !loseRadioButton.isChecked()){

                // Ask the user to enter if they won or lost
                Toast.makeText(getContext(), "Please check if you won or lost.", Toast.LENGTH_SHORT).show();
            } else {

                // Call the save match interface method
                addMatchFragmentInterfaceListener.saveMatch(winRadioButton,
                        loseRadioButton,
                        mapNameEditText,
                        matchLengthEditText,
                        killsEditText,
                        deathsEditText,
                        mainWeaponEditText,
                        secondaryWeaponEditText,
                        grenadesEditText,
                        matchScoreEditText,
                        matchAssistsEditText,
                        matchNotesEditText);
            }

        }
        return true;
    }
}
