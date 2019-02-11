package com.fullsail.christopherfortune.gametrack.LoginSignUpFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fullsail.christopherfortune.gametrack.R;

public class LoginFragment extends Fragment {

    public static final String TAG = "LoginFragment.TAG";
    public LoginFragmentInterface loginFragmentInterfaceListener;

    public interface LoginFragmentInterface{
        void login();
        void signUp();
    }

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LoginFragmentInterface){
            loginFragmentInterfaceListener = (LoginFragmentInterface)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View loginFragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        Button loginButton = loginFragmentView.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentInterfaceListener.login();
            }
        });

        Button signUpButton = loginFragmentView.findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragmentInterfaceListener.signUp();
            }
        });

        return loginFragmentView;
    }
}
