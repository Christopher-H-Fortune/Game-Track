package com.fullsail.christopherfortune.gametrack.AddGameActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.fullsail.christopherfortune.gametrack.AddGameFragment.AddGameFragment;
import com.fullsail.christopherfortune.gametrack.GameClass.Games;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGameActivity extends AppCompatActivity implements AddGameFragment.AddGameFragmentInterface {

    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        setTitle(R.string.add_a_game);

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.add_game_frame_layout,  AddGameFragment.newInstance() ,AddGameFragment.TAG).commit();
    }


    @Override
    public void saveGame(EditText gameTitleEditText, EditText gameDeveloperEditText, EditText gamePublisherEditText, EditText gameReleaseYearEditText, EditText gameLauncherEditText) {

        String gameTitle = gameTitleEditText.getText().toString();
        String gameDeveloper = gameDeveloperEditText.getText().toString();
        String gamePublisher = gamePublisherEditText.getText().toString();
        String gameReleaseYear = gameReleaseYearEditText.getText().toString();
        String gameLauncher = gameLauncherEditText.getText().toString();

        // Get the current user signed in
        FirebaseUser user = mAuth.getCurrentUser();

        Games gameToSave = new Games(gameTitle, gameDeveloper, gamePublisher, gameReleaseYear, gameLauncher);

        // If the user isn't null
        if(user != null){

            // Get the userID
            String userID = user.getUid();

            // Set a reference to our Firebase database
            DatabaseReference databaseReference = mFirebaseDatabase.getReference();

            // Create the user's profile in the database using the user's id
            databaseReference.child("users").child(userID).child("games").child(gameTitle).setValue(gameToSave);
        }
    }
}
