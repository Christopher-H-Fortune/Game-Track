package com.fullsail.christopherfortune.gametrack.CareerFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullsail.christopherfortune.gametrack.R;

public class CareerFragment extends Fragment {

    public static String TAG = "CareerFragment.TAG";

    public CareerFragmentInterface careerFragmentInterfaceListener;

    public interface CareerFragmentInterface{
        void fragmentReady();
    }

    public static CareerFragment newInstance(){
        return new CareerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof CareerFragmentInterface){
            careerFragmentInterfaceListener = (CareerFragmentInterface)context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_career, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        careerFragmentInterfaceListener.fragmentReady();
    }
}
