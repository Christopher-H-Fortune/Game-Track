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

    // String variable to reference the signUpFragment when displaying to activity
    public static final String TAG = "SignUpFragment.TAG";

    // LoginFragmentInterface variable to call the interface methods
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

        // If the context is an instance of the SignUpFragmentInterface
        if(context instanceof SignUpFragmentInterface){

            // Set the signUpFragmentInterfaceListener to the context as a SignUpFragmentInterface
            signUpFragmentInterfaceListener = (SignUpFragmentInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Get the fragment_signup to inflate to the view
        View signUpFragmentView = inflater.inflate(R.layout.fragment_signup, container, false);

        // EditText to obtain the users email and password entered
        final EditText emailEditText = signUpFragmentView.findViewById(R.id.email_sign_up_edit_text);
        final EditText passwordEditText = signUpFragmentView.findViewById(R.id.password_sign_up_edit_text);
        final EditText verifyPasswordEditText = signUpFragmentView.findViewById(R.id.second_password_sign_up_edit_text);

        // Get the create account button to allow the user create their account
        Button createAccountButton = signUpFragmentView.findViewById(R.id.create_account_button);

        // Set the onClickListener to the createAccountButton
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* Call the createAccount method passing the email, password, and verify password
                 editText */
                signUpFragmentInterfaceListener.createAccount(emailEditText, passwordEditText, verifyPasswordEditText);
            }
        });

        // Return the signUpFragmentView
        return signUpFragmentView;
    }
}
