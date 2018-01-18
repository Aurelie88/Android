package com.iut.ecommerce.ecommerce.vue;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.ecommerce.ecommerce.R;

/**
 * Created by Damien on 09/01/2018.
 */

public class ClientView extends Fragment {

    public ClientView() {
        super();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.client_main, container, false);
    }
}
