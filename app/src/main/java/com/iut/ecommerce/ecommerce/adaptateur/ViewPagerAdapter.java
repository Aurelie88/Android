package com.iut.ecommerce.ecommerce.adaptateur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iut.ecommerce.ecommerce.fragment.PromotionView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miljold on 22/01/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList =new ArrayList<>();
    private final List<String> mFragmentTitleList =new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager){
        super(manager);
    }


    @Override
    public Fragment getItem(int position) {

        if (mFragmentList.size()==2 && position <3){
            mFragmentList.add(new PromotionView());
            mFragmentTitleList.add("Promotions");
        }
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment (Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public List<String> getPageTitles() {
        return mFragmentTitleList;
    }
}


