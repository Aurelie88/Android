package com.iut.ecommerce.ecommerce.vue;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.ecommerce.ecommerce.R;


public class PromotionView extends Fragment {

    public PromotionView() {
        super();
    }


    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle("Boutique");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.promotion_main, container, false);
    }
}
