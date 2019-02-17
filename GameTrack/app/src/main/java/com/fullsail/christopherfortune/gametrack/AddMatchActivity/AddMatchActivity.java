package com.fullsail.christopherfortune.gametrack.AddMatchActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fullsail.christopherfortune.gametrack.AddMatchFragment.AddMatchFragment;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.MatchListActivity.MatchListActivity;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddMatchActivity extends AppCompatActivity implements AddMatchFragment.AddMatchFragmentInterface {

    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;
    private String gameChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match);
        setTitle(R.string.add_match);

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the starting intent of the activity
        Intent startIntent = getIntent();

        // Get the string extra passed through the intent
        gameChosen = startIntent.getStringExtra("gameName");

        // Display the AddMatchFragment to the user
        getSupportFragmentManager().beginTransaction().replace(R.id.add_match_frame_layout, AddMatchFragment.newInstance(), AddMatchFragment.TAG).commit();
    }

    @Override
    public void saveMatch(RadioButton winRadioButton, RadioButton loseRadioButton, EditText mapNameEditText, EditText matchLengthEditText, EditText killsEditText, EditText deathsEditText, EditText mainWeaponEditText, EditText secondaryWeaponEditText, EditText grenadesEditText, EditText matchScoreEditText, EditText matchAssistEditText, EditText matchNotesEditText) {

        // Get the current user signed in
        FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user isn't null
        if(user != null){

            // Set the default value of game won to false
            boolean gameWon = false;

            // If the win radio button is checked
            if(winRadioButton.isChecked()){
                gameWon = true;
            }

            // Data to the user entered to store to the database
            String mapName = mapNameEditText.getText().toString().trim();
            String matchLength = matchLengthEditText.getText().toString().trim();
            String killsString = killsEditText.getText().toString().trim();
            int killsCount = Integer.parseInt(killsString);
            String deathsString = deathsEditText.getText().toString().trim();
            int deathCount = Integer.parseInt(deathsString);
            String mainWeapon = mainWeaponEditText.getText().toString().trim();
            String secondaryWeapon = secondaryWeaponEditText.getText().toString().trim();
            String grenades = grenadesEditText.getText().toString().trim();
            String matchScoreString = matchScoreEditText.getText().toString().trim();
            int matchScore = Integer.parseInt(matchScoreString);
            String matchAssistString = matchAssistEditText.getText().toString().trim();
            int matchAssist = Integer.parseInt(matchAssistString);
            String matchNotesString = matchNotesEditText.getText().toString().trim();

            Calendar calendar = Calendar.getInstance();

            Date currentDate = calendar.getTime();

            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyy HH:mm:ss");

            String dateString = dateFormat.format(currentDate);

            // Store the data above into a Matches class object
            Matches matchEntered = new Matches(dateString,
                    gameWon,
                    mapName,
                    matchLength,
                    killsCount,
                    deathCount,
                    mainWeapon,
                    secondaryWeapon,
                    grenades,
                    matchScore,
                    matchAssist,
                    matchNotesString);

            // Get the users ID
            String userID = user.getUid();

            // Database reference to set the reference on where to store the match data
            DatabaseReference databaseReference = mFirebaseDatabase.getReference();

            // Set the reference on where to store match data
            databaseReference.child("users").child(userID).child("games").child(gameChosen).child("matches").child(dateString).setValue(matchEntered);

            Toast.makeText(this, "Match Saved", Toast.LENGTH_SHORT).show();

            Intent matchListIntent = new Intent(this, MatchListActivity.class);
            matchListIntent.putExtra("gameChosen", gameChosen);
            startActivity(matchListIntent);
        }
    }
}
