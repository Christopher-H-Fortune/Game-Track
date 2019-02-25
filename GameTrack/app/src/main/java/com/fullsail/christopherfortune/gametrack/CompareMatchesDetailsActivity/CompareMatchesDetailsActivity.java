package com.fullsail.christopherfortune.gametrack.CompareMatchesDetailsActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.CompareMatchesDetailsFragment.CompareMatchesDetailsFragment;
import com.fullsail.christopherfortune.gametrack.CompareMatchesFragment.CompareMatchesFragment;
import com.fullsail.christopherfortune.gametrack.MatchClass.Matches;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompareMatchesDetailsActivity extends AppCompatActivity implements CompareMatchesDetailsFragment.CompareMatchesDetailsFragmentInterface {

    public String gameChosen;
    public String firstMatchDate;
    public String secondMatchDate;
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    TextView matchesComparedTitleTextView;
    TextView killDiffTextView;
    TextView assistDiffTextView;
    TextView scoreDiffTextView;
    EditText firstMainWeaponEditText;
    EditText secondMainWeaponEditText;
    EditText firstSecondaryWeaponEditText;
    EditText secondSecondaryWeaponEditText;
    EditText firstGrenadesEditText;
    EditText secondGrenadesEditText;
    EditText firstWinLossEditText;
    EditText secondWinLossEditText;

    @Override
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
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

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


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Display the CompareMatchesFragment in the compare_matches_frame_layout
        getSupportFragmentManager().beginTransaction().replace(R.id.compare_matches_details_frame_layout, CompareMatchesFragment.newInstance(), CompareMatchesFragment.TAG).commit();
    }

    @Override
    public void passViews(TextView matchesComparedTitleTextView, TextView killDiffTextView, TextView assistDiffTextView, TextView scoreDiffTextView, EditText firstMainWeaponEditText, EditText secondMainWeaponEditText, EditText firstSecondaryWeaponEditText, EditText secondSecondaryWeaponEditText, EditText firstGrenadesEditText, EditText secondGrenadesEditText, EditText firstWinLossEditText, EditText secondWinLossEditText) {
        this.matchesComparedTitleTextView = matchesComparedTitleTextView;
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
