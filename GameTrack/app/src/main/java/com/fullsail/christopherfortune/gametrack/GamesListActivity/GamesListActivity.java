package com.fullsail.christopherfortune.gametrack.GamesListActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.fullsail.christopherfortune.gametrack.AddGameActivity.AddGameActivity;
import com.fullsail.christopherfortune.gametrack.GameClass.Games;
import com.fullsail.christopherfortune.gametrack.GameListFragment.GamesListFragment;
import com.fullsail.christopherfortune.gametrack.MatchListActivity.MatchListActivity;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.fullsail.christopherfortune.gametrack.GameListFragment.GamesListAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class GamesListActivity extends AppCompatActivity implements GamesListFragment.GamesListFragmentInterface {

    public FirebaseAuth mAuth;
    public DatabaseReference mDatabaseReference;
    public FirebaseDatabase mFirebaseDatabase;
    public ArrayList<String> gamesArrayList = new ArrayList<>();
    public ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);
        setTitle("Welcome!");

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Get the current user signed in
        final FirebaseUser user = mAuth.getCurrentUser();

        // Make sure the user ins't null
        if(user != null){

            // Get the users ID
            final String uId = user.getUid();

            // Set the database reference using the mFirebaseDatabase
            mDatabaseReference = mFirebaseDatabase.getReference("/users/"+ uId + "/games");

            // Set the database to have a ValueEventListener to display the games data to the user
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    // Loop through the data snapshot
                    for(DataSnapshot data : dataSnapshot.getChildren()){

                        // Get the games data
                        Games games = data.getValue(Games.class);

                        // Make sure games isn't null
                        if (games != null) {

                            // Get the game title
                            String gameTitle = games.getGameTitle();

                            // Add the game title to the gamesArrayList
                            gamesArrayList.add(gameTitle);
                        }
                    }

                    updateGamesList();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        // Display the gamesListFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.game_list_frame_layout, GamesListFragment.newInstance(), GamesListFragment.TAG).commit();
    }

    @Override
    public void addGame() {

        // Intent to send the user to the add game activity
        Intent addGameIntent = new Intent(GamesListActivity.this, AddGameActivity.class);

        // Start the AddGameActivity with the intent created above
        startActivity(addGameIntent);
    }

    private void updateGamesList(){
        // String array to save the keys created above
        String[] keys = new String[]{"keyGameTitle"};

        // Int array to save the view id's to display the data of each game in the child views
        int[] viewIDs = new int[]{R.id.game_name_text_view};

        // ArrayList of type HashMap key string and value string
        ArrayList<HashMap<String, String>> postDataCollection = new ArrayList<>();

        // For every game in the gamesArrayList
        for (String game : gamesArrayList) {

            // HashMap to assign each value and key string pairs
            HashMap<String, String> map = new HashMap<>();

            // Assign the data to the keys
            map.put("keyGameTitle", game);

            // Add the HashMap to the Array List created above
            postDataCollection.add(map);
        }

        // Create the Custom Adapter
        GamesListAdapter listViewAdapter = new GamesListAdapter(this, postDataCollection, keys, viewIDs);

        // Check that the gamesListView isn't null before setting the adapter
        if(gamesListView != null){

            // Set the gamesListView adapter to the custom adapter created above
            gamesListView.setAdapter(listViewAdapter);
        }
    }

    @Override
    public void passListView(ListView gamesListView) {
        this.gamesListView = gamesListView;
    }

    @Override
    public void viewGame(int gameChosen) {

        // Get the game chosen to pass in the intent
        String gameChosenTitle = gamesArrayList.get(gameChosen);

        // Intent to send the user to the selected game screen
        Intent selectedGameIntent = new Intent(this, MatchListActivity.class);

        // Put the gameChosenTitle as an extra in the intent created above
        selectedGameIntent.putExtra("gameChosen", gameChosenTitle);

        // Start the MatchListActivity with the intent created above
        startActivity(selectedGameIntent);
    }

}
