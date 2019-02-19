package com.fullsail.christopherfortune.gametrack.SelectedMatchFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullsail.christopherfortune.gametrack.R;

public class SelectedMatchFragment extends Fragment {

    // String variable to reference the AddGameFragment when displaying
    public static final String TAG = "SelectedMatchFragment.TAG";

    public static SelectedMatchFragment newInstance(){
        return new SelectedMatchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View selectedMatchFragmentView = inflater.inflate(R.layout.fragment_selected_match, container, false);


        return selectedMatchFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
