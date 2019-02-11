package com.fullsail.christopherfortune.gametrack.LoginSignUpActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.fullsail.christopherfortune.gametrack.LoginSignUpFragments.LoginFragment;
import com.fullsail.christopherfortune.gametrack.LoginSignUpFragments.SignUpFragment;
import com.fullsail.christopherfortune.gametrack.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInterface, SignUpFragment.SignUpFragmentInterface {

    public FirebaseAuth mAuth;
    public FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set the instance of the Firebase auth and database
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // Display the LoginFragment to the user
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame_layout, LoginFragment.newInstance(), LoginFragment.TAG).commit();
    }

    @Override
    public void login(EditText emailEditText, EditText passwordEditText) {

        // String variables to get the data from the edit text and verify the data entered
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // If there isn't an email entered
        if(email.isEmpty()){

            // Ask the user to enter a email to create an account
            Toast.makeText(this, "Please enter a email.", Toast.LENGTH_SHORT).show();

            // If the email isn't valid
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            // Ask the user to enter a valid email to create an account
            Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();

            // Clear the email edit text
            emailEditText.setText(null);
        }

        // If a password isn't entered
        if(password.isEmpty()){

            // Ask the user to enter a password to create an account
            Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show();
        }

        // If the password isn't long enough
        if(password.length() < 6){

            // Ask the user to enter their password again to login
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();

            // Clear the password Edit Text
            passwordEditText.setText(null);
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // If login was successful
                if(task.isSuccessful()){

                    // Display the message to the user
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                // If the login wasn't successful
                } else {

                    // IF the exception isn't null
                    if(task.getException() != null){

                        // Display the message to the user
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void signUp() {

        // Display the SignUpFragment to the user to allow them to create an account
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame_layout, SignUpFragment.newInstance(), SignUpFragment.TAG).addToBackStack(null).commit();
    }

    @Override
    public void createAccount(EditText emailEditText, EditText passwordEditText, EditText verifyPasswordEditText) {

        // String variables to get the data from the edit text and verify the data entered
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String verifyPassword = verifyPasswordEditText.getText().toString().trim();

        // If there isn't an email entered
        if(email.isEmpty()){

            // Ask the user to enter a email to create an account
            Toast.makeText(this, "Please enter a email.", Toast.LENGTH_SHORT).show();

        // If the email isn't valid
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            // Ask the user to enter a valid email to create an account
            Toast.makeText(this, "Please enter a valid email.", Toast.LENGTH_SHORT).show();

            // Clear the email edit text
            emailEditText.setText(null);
        }

        // If a password isn't entered
        if(password.isEmpty()){

            // Ask the user to enter a password to create an account
            Toast.makeText(this, "Please enter a password.", Toast.LENGTH_SHORT).show();
        }

        // If a verify password isn't entered
        if(verifyPassword.isEmpty()){

            // Ask the user to enter their password again to create an account
            Toast.makeText(this, "Please enter your password again.", Toast.LENGTH_SHORT).show();
        }

        // If the passwords don't match
        if(!verifyPassword.equals(password)){

            // Ask the user to enter their password again to create an account
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();

            // Clear the password Edit Text lines
            passwordEditText.setText(null);
            verifyPasswordEditText.setText(null);
        }

        // If the passwords match but aren't at least 4 characters long
        if(verifyPassword.equals(password) && password.length() < 6){

            // Ask the user to enter their password again to create an account
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show();

            // Clear the password Edit Text lines
            passwordEditText.setText(null);
            verifyPasswordEditText.setText(null);
        }

        // If all the conditionals are met, create the users account
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // If the account was created
                if(task.isSuccessful()){

                    // Ask the user to enter their password again to create an account
                    Toast.makeText(getApplicationContext(), "Account Created! Welcome!", Toast.LENGTH_SHORT).show();

                    // Sign in the newly created user
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            // If login was successful
                            if(task.isSuccessful()){

                                // Display the message to the user
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                                // Get the current user signed in
                                FirebaseUser user = mAuth.getCurrentUser();

                                // If the user isn't null
                                if(user != null){

                                    // Get the userID
                                    String userID = user.getUid();

                                    // Set a reference to our Firebase database
                                    DatabaseReference databaseReference = mFirebaseDatabase.getReference();

                                    // Create the user's profile in the database using the user's id
                                    databaseReference.child("users").child(userID).setValue(userID);
                                }

                            // If there was an error in creating the account
                            } else {

                                // IF the exception isn't null
                                if(task.getException() != null){

                                    // If the user already exists
                                    if(task.getException() instanceof FirebaseAuthUserCollisionException){

                                        // Display the message to the user
                                        Toast.makeText(getApplicationContext(),"Account already created.", Toast.LENGTH_SHORT).show();

                                    // Display message in error of creating user
                                    } else {
                                        // Display the message to the user
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });

                // If the account wasn't created
                } else {

                    // Ask the user to try again
                    Toast.makeText(getApplicationContext(), "Sign-Up failed. Pleas Try again.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
