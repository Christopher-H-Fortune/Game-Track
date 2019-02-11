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
import android.widget.EditText;

import com.fullsail.christopherfortune.gametrack.R;

public class SignUpFragment extends Fragment {

    public static final String TAG = "LoginFragment.TAG";
    public SignUpFragmentInterface signUpFragmentInterfaceListener;

    public interface SignUpFragmentInterface{
        void createAccount(EditText emailEditText, EditText passwordEditText, EditText verifyPasswordEditText);
    }

    public static SignUpFragment newInstance(){
        return new SignUpFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof SignUpFragmentInterface){
            signUpFragmentInterfaceListener = (SignUpFragmentInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View signUpFragmentView = inflater.inflate(R.layout.fragment_signup, container, false);

        Button createAccountButton = signUpFragmentView.findViewById(R.id.create_account_button);

        final EditText emailEditText = signUpFragmentView.findViewById(R.id.email_sign_up_edit_text);
        final EditText passwordEditText = signUpFragmentView.findViewById(R.id.password_sign_up_edit_text);
        final EditText verifyPasswordEditText = signUpFragmentView.findViewById(R.id.second_password_sign_up_edit_text);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpFragmentInterfaceListener.createAccount(emailEditText, passwordEditText, verifyPasswordEditText);
            }
        });

        return signUpFragmentView;
    }
}
