package com.fullsail.christopherfortune.gametrack.CompareMatchesDetailsFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fullsail.christopherfortune.gametrack.R;

public class CompareMatchesDetailsFragment extends Fragment {

    private TextView matchesComparedTitleTextView;
    private TextView firstMapNameTextView;
    private TextView secondMapNameTextView;
    private TextView killDiffTextView;
    private TextView assistDiffTextView;
    private TextView scoreDiffTextView;
    private EditText firstMainWeaponEditText;
    private EditText secondMainWeaponEditText;
    private EditText firstSecondaryWeaponEditText;
    private EditText secondSecondaryWeaponEditText;
    private EditText firstGrenadesEditText;
    private EditText secondGrenadesEditText;
    private EditText firstWinLossEditText;
    private EditText secondWinLossEditText;

    // String variable to reference the CompareMatchesDetailsFragment when displaying
    public static final String TAG = "CompareMatchesDetailsFragment.TAG";

    private static CompareMatchesDetailsFragmentInterface compareMatchesDetailsFragmentInterfaceListener;

    public interface CompareMatchesDetailsFragmentInterface{
        void passViews(TextView matchesComparedTitleTextView,
                       TextView firstMapNameTextView,
                       TextView secondMapNameTextView,
                       TextView killDiffTextView,
                TextView assistDiffTextView,
                TextView scoreDiffTextView,
                EditText firstMainWeaponEditText,
                EditText secondMainWeaponEditText,
                EditText firstSecondaryWeaponEditText,
                EditText secondSecondaryWeaponEditText,
                EditText firstGrenadesEditText,
                EditText secondGrenadesEditText,
                EditText firstWinLossEditText,
                EditText secondWinLossEditText);
        void doneComparing();
    }

    public static CompareMatchesDetailsFragment newInstance(){
        return new CompareMatchesDetailsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CompareMatchesDetailsFragmentInterface){
            compareMatchesDetailsFragmentInterfaceListener = (CompareMatchesDetailsFragmentInterface) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View compareMatchesDetailsFragmentView = inflater.inflate(R.layout.fragment_compare_matches_details, container, false);

        matchesComparedTitleTextView = compareMatchesDetailsFragmentView.findViewById(R.id.matches_compared_title_text_view);
        firstMapNameTextView = compareMatchesDetailsFragmentView.findViewById(R.id.first_map_name_compare_text_view);
        secondMapNameTextView = compareMatchesDetailsFragmentView.findViewById(R.id.second_map_name_compare_text_view);
        killDiffTextView = compareMatchesDetailsFragmentView.findViewById(R.id.kill_difference_text_view);
        assistDiffTextView = compareMatchesDetailsFragmentView.findViewById(R.id.assists_difference_text_view);
        scoreDiffTextView = compareMatchesDetailsFragmentView.findViewById(R.id.score_difference_text_view);
        firstMainWeaponEditText = compareMatchesDetailsFragmentView.findViewById(R.id.first_main_weapon_edit_text);
        secondMainWeaponEditText = compareMatchesDetailsFragmentView.findViewById(R.id.second_main_weapon_edit_text);
        firstSecondaryWeaponEditText = compareMatchesDetailsFragmentView.findViewById(R.id.first_secondary_weapon_edit_text);
        secondSecondaryWeaponEditText = compareMatchesDetailsFragmentView.findViewById(R.id.second_secondary_weapon_edit_text);
        firstGrenadesEditText = compareMatchesDetailsFragmentView.findViewById(R.id.first_grenade_used_edit_text);
        secondGrenadesEditText = compareMatchesDetailsFragmentView.findViewById(R.id.second_grenade_used_edit_text);
        firstWinLossEditText = compareMatchesDetailsFragmentView.findViewById(R.id.first_win_loss_edit_text);
        secondWinLossEditText = compareMatchesDetailsFragmentView.findViewById(R.id.second_win_loss_edit_text);

        return compareMatchesDetailsFragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        compareMatchesDetailsFragmentInterfaceListener.passViews(matchesComparedTitleTextView,
                firstMapNameTextView,
                secondMapNameTextView,
                killDiffTextView,
                assistDiffTextView,
                scoreDiffTextView,
                firstMainWeaponEditText,
                secondMainWeaponEditText,
                firstSecondaryWeaponEditText,
                secondSecondaryWeaponEditText,
                firstGrenadesEditText,
                secondGrenadesEditText,
                firstWinLossEditText,
                secondWinLossEditText);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.compare_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.done_compare_menu_button){
            compareMatchesDetailsFragmentInterfaceListener.doneComparing();
        }

        return true;
    }
}
