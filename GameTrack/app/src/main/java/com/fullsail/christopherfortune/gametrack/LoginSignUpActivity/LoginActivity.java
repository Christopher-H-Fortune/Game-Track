package com.fullsail.christopherfortune.gametrack.LoginSignUpActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentInterface, SignUpFragment.SignUpFragmentInterface {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame_layout, LoginFragment.newInstance(), LoginFragment.TAG).commit();
    }

    @Override
    public void login() {

    }

    @Override
    public void signUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.login_frame_layout, SignUpFragment.newInstance(), SignUpFragment.TAG).addToBackStack(null).commit();
    }

    @Override
    public void createAccount(EditText emailEditText, EditText passwordEditText, EditText verifyPasswordEditText) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String verifyPassword = verifyPasswordEditText.getText().toString().trim();

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
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();

            // Clear the password Edit Text lines
            passwordEditText.setText(null);
            verifyPasswordEditText.setText(null);
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // If the account was created
                if(task.isSuccessful()){

                    // Ask the user to enter their password again to create an account
                    Toast.makeText(getApplicationContext(), "Account Created! Welcome!", Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity.TAG", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), task.getException().toString(),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
