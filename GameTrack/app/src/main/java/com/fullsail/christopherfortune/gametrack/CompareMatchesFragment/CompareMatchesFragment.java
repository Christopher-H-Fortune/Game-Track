package com.fullsail.christopherfortune.gametrack.CompareMatchesFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import com.fullsail.christopherfortune.gametrack.R;

public class CompareMatchesFragment extends Fragment {

    private Spinner firstMatchSpinner;
    private Spinner secondMatchSpinner;

    private CompareMatchesFragmentInterface compareMatchesFragmentInterfaceListener;

    // String variable to reference the CompareMatchesFragment when displaying
    public static final String TAG = "CompareMatchesFragment.TAG";

    public interface CompareMatchesFragmentInterface{
        void passSpinners(Spinner firstMatchSpinner, Spinner secondMatchSpinner);
        void compareGames();
    }

    public static CompareMatchesFragment newInstance(){
        return new CompareMatchesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CompareMatchesFragmentInterface){
            compareMatchesFragmentInterfaceListener = (CompareMatchesFragmentInterface)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View compareMatchesFragmentView = inflater.inflate(R.layout.fragment_compare_matches, container, false);

        Button compareMatchesButton = compareMatchesFragmentView.findViewById(R.id.compare_matches_button);

        compareMatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareMatchesFragmentInterfaceListener.compareGames();
            }
        });

        firstMatchSpinner = compareMatchesFragmentView.findViewById(R.id.first_match_spinner);

        secondMatchSpinner = compareMatchesFragmentView.findViewById(R.id.second_match_spinner);

        return compareMatchesFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compareMatchesFragmentInterfaceListener.passSpinners(firstMatchSpinner, secondMatchSpinner);
    }
}
