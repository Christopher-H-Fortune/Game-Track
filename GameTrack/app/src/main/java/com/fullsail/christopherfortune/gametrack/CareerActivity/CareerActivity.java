package com.fullsail.christopherfortune.gametrack.CareerActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.CareerFragment.CareerFragment;
import com.fullsail.christopherfortune.gametrack.CompareMatchesActivity.CompareMatchesActivity;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.MatchListActivity.MatchListActivity;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CareerActivity extends AppCompatActivity implements CareerFragment.CareerFragmentInterface {

    private String gameChosen;
    private DatabaseReference mDatabaseReference;
    private ArrayList<Matches> careerMatchesArrayList;
    private boolean fragmentReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);

        careerMatchesArrayList = new ArrayList<>();

        // Get the starting intent
        Intent startingIntent = getIntent();

        // If the starting intent has an extra called gameChosen
        if(startingIntent.hasExtra("gameChosen")){

            // Store the string extra passed from the intent
            gameChosen = startingIntent.getStringExtra("gameChosen");
        }

        // Set the instance of the Firebase auth and database
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the current user signed in
        final FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null) {

            // Get the users ID
            final String uId = user.getUid();

            // Set the database reference using the mFirebaseDatabase
            mDatabaseReference = mFirebaseDatabase.getReference("/users/" + uId + "/games/" + gameChosen + "/matches");
        }

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Loop through the data snapshot
                for(DataSnapshot data : dataSnapshot.getChildren()){

                    // Get the games data
                    Matches matches = data.getValue(Matches.class);

                    // Make sure games isn't null
                    if (matches != null) {

                        String matchDate = matches.getMatchDate();

                        boolean gameWon = matches.isGameWon();

                        String mapName = matches.getMapName();

                        String matchLength = matches.getMatchLength();

                        int matchKills = matches.getKills();

                        int matchDeaths = matches.getDeaths();

                        String mainWeapon = matches.getMainWeapon();

                        String secondaryWeapon = matches.getSecondaryWeapon();

                        String grenades = matches.getGrenades();

                        int matchScore = matches.getMatchScore();

                        int matchAssists = matches.getMatchAssists();

                        String matchNotes = matches.getMatchNotes();

                        careerMatchesArrayList.add(new Matches(matchDate,
                                gameWon,
                                mapName,
                                matchLength,
                                matchKills,
                                matchDeaths,
                                mainWeapon,
                                secondaryWeapon,
                                grenades,
                                matchScore,
                                matchAssists,
                                matchNotes));
                    }
                }

                displayCareerData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ImageButton matchListImageButton = findViewById(R.id.matches_list_image_button);
        matchListImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent matchListIntent = new Intent(CareerActivity.this, MatchListActivity.class);
                matchListIntent.putExtra("gameChosen", gameChosen);
                startActivity(matchListIntent);
            }
        });

        ImageButton compareMatchesImageButton = findViewById(R.id.compare_image_button);
        compareMatchesImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent compareMatchesIntent = new Intent(CareerActivity.this, CompareMatchesActivity.class);
                compareMatchesIntent.putExtra("gameChosen", gameChosen);
                startActivity(compareMatchesIntent);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.career_frame_layout, CareerFragment.newInstance(), CareerFragment.TAG).commit();
    }

    private void displayCareerData(){

        int killsTotal = 0;
        int assistTotal = 0;
        int scoreTotal = 0;
        int careerWins = 0;
        int careerLosses = 0;

        for(Matches match: careerMatchesArrayList){

            killsTotal += match.getKills();
            assistTotal += match.getMatchAssists();
            scoreTotal += match.getMatchScore();

            if(match.isGameWon()){
                careerWins += 1;
            } else {
                careerLosses += 1;
            }
        }

        int killsAverage = killsTotal / careerMatchesArrayList.size();
        int assistAverage = assistTotal / careerMatchesArrayList.size();
        int scoreAverage = scoreTotal / careerMatchesArrayList.size();

        TextView gameCareerTitleTextView = findViewById(R.id.game_career_title_text_view);
        gameCareerTitleTextView.setText(getString(R.string.career_title, gameChosen));

        TextView careerKillsTextView = findViewById(R.id.career_kills_text_view);
        if(killsAverage >= 0){
            careerKillsTextView.setText(getString(R.string.compare_kills_positive, killsAverage));
        } else {
            careerKillsTextView.setText(getString(R.string.compare_kills, killsAverage));
        }

        TextView careerAssistTextView = findViewById(R.id.career_assist_text_view);
        if(assistAverage >= 0){
            careerAssistTextView.setText(getString(R.string.compare_assist_positive, assistAverage));
        } else {
            careerAssistTextView.setText(getString(R.string.assist_text_view, assistAverage));
        }

        TextView careerScoreTextView = findViewById(R.id.career_score_text_view);
        if(scoreAverage >= 0){
            careerScoreTextView.setText(getString(R.string.compare_score_positive, scoreAverage));
        } else {
            careerScoreTextView.setText(getString(R.string.score_text_view, scoreAverage));
        }

        EditText careerWinsEditText = findViewById(R.id.career_wins_edit_text);
        careerWinsEditText.setText(getString(R.string.win_count, careerWins));

        EditText careerLossesEditText = findViewById(R.id.career_losses_edit_text);
        careerLossesEditText.setText(getString(R.string.loss_count, careerLosses));
    }

    @Override
    public void fragmentReady() {
        fragmentReady = true;
    }
}
