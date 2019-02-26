package com.fullsail.christopherfortune.gametrack.CompareMatchesDetailsActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.CompareMatchesDetailsFragment.CompareMatchesDetailsFragment;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.perf.metrics.AddTrace;

public class CompareMatchesDetailsActivity extends AppCompatActivity implements CompareMatchesDetailsFragment.CompareMatchesDetailsFragmentInterface {

    private String gameChosen;
    private String firstMatchDate;
    private String secondMatchDate;
    private DatabaseReference mDatabaseReference;
    private Matches firstMatch;
    private Matches secondMatch;
    private TextView matchesComparedTitleTextView;
    private TextView firstMapNameTextView;
    private TextView secondMapNameTextView;
    private TextView killDiffTextView;
    private TextView assistDiffTextView;
    private TextView scoreDiffTextView;
    private EditText firstMainWeaponEditText;
    private EditText secondMainWeaponEditText;
    private EditText firstSecondaryWeaponEditText;
    private EditText secondSecondaryWeaponEditText;
    private EditText firstGrenadesEditText;
    private EditText secondGrenadesEditText;
    private EditText firstWinLossEditText;
    private EditText secondWinLossEditText;


    @Override
    @AddTrace(name = "onCreateCompareTrace")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_matches_details);

        // Get the starting intent
        Intent startingIntent = getIntent();

        if(startingIntent.hasExtra("gameChosen")){

            gameChosen = startingIntent.getStringExtra("gameChosen");
        }

        if(startingIntent.hasExtra("firstMatchDate")){

            firstMatchDate = startingIntent.getStringExtra("firstMatchDate");
        }

        if(startingIntent.hasExtra("secondMatchDate")){

            secondMatchDate = startingIntent.getStringExtra("secondMatchDate");
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

                        if(firstMatchDate.equals(matches.getMatchDate())){

                            firstMatch = new Matches();
                            firstMatch.setMatchDate(matches.getMatchDate());
                            firstMatch.setGameWon(matches.isGameWon());
                            firstMatch.setMapName(matches.getMapName());
                            firstMatch.setMatchLength(matches.getMatchLength());
                            firstMatch.setKills(matches.getKills());
                            firstMatch.setDeaths(matches.getDeaths());
                            firstMatch.setMainWeapon(matches.getMainWeapon());
                            firstMatch.setSecondaryWeapon(matches.getSecondaryWeapon());
                            firstMatch.setGrenades(matches.getGrenades());
                            firstMatch.setMatchScore(matches.getMatchScore());
                            firstMatch.setMatchAssists(matches.getMatchAssists());
                            firstMatch.setMatchNotes(matches.getMatchNotes());
                        }

                        if(secondMatchDate.equals(matches.getMatchDate())){

                            secondMatch = new Matches();
                            secondMatch.setMatchDate(matches.getMatchDate());
                            secondMatch.setGameWon(matches.isGameWon());
                            secondMatch.setMapName(matches.getMapName());
                            secondMatch.setMatchLength(matches.getMatchLength());
                            secondMatch.setKills(matches.getKills());
                            secondMatch.setDeaths(matches.getDeaths());
                            secondMatch.setMainWeapon(matches.getMainWeapon());
                            secondMatch.setSecondaryWeapon(matches.getSecondaryWeapon());
                            secondMatch.setGrenades(matches.getGrenades());
                            secondMatch.setMatchScore(matches.getMatchScore());
                            secondMatch.setMatchAssists(matches.getMatchAssists());
                            secondMatch.setMatchNotes(matches.getMatchNotes());
                        }

                    }
                }

                displayDetails();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Display the CompareMatchesFragment in the compare_matches_frame_layout
        getSupportFragmentManager().beginTransaction().replace(R.id.compare_matches_details_frame_layout, CompareMatchesDetailsFragment.newInstance(), CompareMatchesDetailsFragment.TAG).commit();
    }

    @AddTrace(name = "displayDetailsTrace")
    private void displayDetails(){

        if(matchesComparedTitleTextView != null){
            matchesComparedTitleTextView.setText(getString(R.string.compare_title, firstMatch.getMapName(), secondMatch.getMapName()));
        }

        if(killDiffTextView != null){

            int killDiff = firstMatch.getKills() - secondMatch.getKills();
            killDiffTextView.setText(getString(R.string.compare_kills, killDiff));
        }

        if(assistDiffTextView != null){

            int assistDiff = firstMatch.getMatchAssists() - secondMatch.getMatchAssists();
            assistDiffTextView.setText(getString(R.string.assist_text_view, assistDiff));
        }

        if(scoreDiffTextView != null){

            int scoreDiff = firstMatch.getMatchScore() - secondMatch.getMatchScore();
            scoreDiffTextView.setText(getString(R.string.score_text_view, scoreDiff));
        }

        if(firstMapNameTextView != null){
            firstMapNameTextView.setText(firstMatch.getMapName());
        }

        if(secondMapNameTextView != null){
            secondMapNameTextView.setText(secondMatch.getMapName());
        }

        if(firstMainWeaponEditText != null){
            firstMainWeaponEditText.setText(firstMatch.getMainWeapon());
        }

        if(secondMainWeaponEditText != null){
            secondMainWeaponEditText.setText(secondMatch.getMainWeapon());
        }

        if(firstSecondaryWeaponEditText != null){
            firstSecondaryWeaponEditText.setText(firstMatch.getSecondaryWeapon());
        }

        if(secondSecondaryWeaponEditText != null){
            secondSecondaryWeaponEditText.setText(secondMatch.getSecondaryWeapon());
        }

        if(firstGrenadesEditText != null){
            firstGrenadesEditText.setText(firstMatch.getGrenades());
        }

        if(secondGrenadesEditText != null){
            secondGrenadesEditText.setText(secondMatch.getGrenades());
        }

        if(firstWinLossEditText != null){
            if(firstMatch.isGameWon()){
                firstWinLossEditText.setText(R.string.match_won);
            } else {
                firstWinLossEditText.setText(R.string.match_lost);
            }
        }

        if(secondWinLossEditText != null){
            if(secondMatch.isGameWon()){
                secondWinLossEditText.setText(R.string.match_won);
            } else {
                secondWinLossEditText.setText(R.string.match_lost);
            }
        }
    }

    @Override
    public void passViews(TextView matchesComparedTitleTextView, TextView killDiffTextView, TextView firstMapNameTextView, TextView secondMapNameTextView, TextView assistDiffTextView, TextView scoreDiffTextView, EditText firstMainWeaponEditText, EditText secondMainWeaponEditText, EditText firstSecondaryWeaponEditText, EditText secondSecondaryWeaponEditText, EditText firstGrenadesEditText, EditText secondGrenadesEditText, EditText firstWinLossEditText, EditText secondWinLossEditText) {
        this.matchesComparedTitleTextView = matchesComparedTitleTextView;
        this.firstMapNameTextView = firstMapNameTextView;
        this.secondMapNameTextView = secondMapNameTextView;
        this.killDiffTextView = killDiffTextView;
        this.assistDiffTextView = assistDiffTextView;
        this.scoreDiffTextView = scoreDiffTextView;
        this.firstMainWeaponEditText = firstMainWeaponEditText;
        this.secondMainWeaponEditText = secondMainWeaponEditText;
        this.firstSecondaryWeaponEditText = firstSecondaryWeaponEditText;
        this.secondSecondaryWeaponEditText = secondSecondaryWeaponEditText;
        this.firstGrenadesEditText = firstGrenadesEditText;
        this.secondGrenadesEditText = secondGrenadesEditText;
        this.firstWinLossEditText = firstWinLossEditText;
        this.secondWinLossEditText = secondWinLossEditText;
    }
}
