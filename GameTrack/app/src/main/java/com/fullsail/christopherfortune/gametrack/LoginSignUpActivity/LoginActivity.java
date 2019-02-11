package com.fullsail.christopherfortune.gametrack.LoginSignUpActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fullsail.christopherfortune.gametrack.LoginSignUpFragments.LoginFragment;
import com.fullsail.christopherfortune.gametrack.LoginSignUpFragments.SignUpFragment;
import com.fullsail.christopherfortune.gametrack.R;
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
    public void createAccount() {

    }
}
