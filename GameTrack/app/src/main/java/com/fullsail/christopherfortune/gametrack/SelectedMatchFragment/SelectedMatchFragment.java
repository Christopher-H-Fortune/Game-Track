package com.fullsail.christopherfortune.gametrack.SelectedMatchFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.R;

public class SelectedMatchFragment extends Fragment {

    // String variable to reference the AddGameFragment when displaying
    public static final String TAG = "SelectedMatchFragment.TAG";

    public SelectedMatchFragmentInterface selectedMatchFragmentInterfaceListener;

    public interface SelectedMatchFragmentInterface{
        void passDataViews(TextView mapNameTextView,
                           TextView matchDateTextView,
                           TextView matchAssistTextView,
                           TextView matchKillsTextView,
                           TextView matchScoreTextView,
                           EditText mainWeaponEditText,
                           EditText secondaryEditText,
                           EditText grenadesEditText,
                           EditText winLossEditText,
                           EditText matchNotesEditText);
    }

    public static SelectedMatchFragment newInstance(){
        return new SelectedMatchFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof SelectedMatchFragmentInterface){
            selectedMatchFragmentInterfaceListener = (SelectedMatchFragmentInterface)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View selectedMatchFragmentView = inflater.inflate(R.layout.fragment_selected_match, container, false);

        TextView mapNameTextView = selectedMatchFragmentView.findViewById(R.id.selected_match_map_name_txt_vw);
        TextView matchDateTextView = selectedMatchFragmentView.findViewById(R.id.selected_match_date_text_view);
        TextView matchAssistTextView = selectedMatchFragmentView.findViewById(R.id.selected_match_assist_text_view);
        TextView matchKillsTextView = selectedMatchFragmentView.findViewById(R.id.selected_match_kills_text_view);
        TextView matchScoreTextView = selectedMatchFragmentView.findViewById(R.id.selected_match_score_text_view);
        EditText mainWeaponEditText = selectedMatchFragmentView.findViewById(R.id.selected_match_main_weapon_edit_text);
        EditText secondaryEditText = selectedMatchFragmentView.findViewById(R.id.selected_match_secondary_edit_text);
        EditText grenadesEditText = selectedMatchFragmentView.findViewById(R.id.selected_match_grenades_edit_text);
        EditText winLossEditText = selectedMatchFragmentView.findViewById(R.id.selected_match_win_loss_edit_text);
        EditText matchNotesEditText = selectedMatchFragmentView.findViewById(R.id.selected_match_match_notes_edit_text);

        selectedMatchFragmentInterfaceListener.passDataViews(mapNameTextView,
                matchDateTextView,
                matchAssistTextView,
                matchKillsTextView,
                matchScoreTextView,
                mainWeaponEditText,
                secondaryEditText,
                grenadesEditText,
                winLossEditText,
                matchNotesEditText);

        return selectedMatchFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
