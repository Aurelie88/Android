package com.iut.ecommerce.ecommerce.adaptateur;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.fragment.PromotionView;
import com.iut.ecommerce.ecommerce.modele.Promotion;

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


    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mFragmentList.get(position);
            case 1:
                return mFragmentList.get(position);
            case 2:
                return mFragmentList.get(position);
            default:
                // Ne devrait pas arriver
                return mFragmentList.get(0);
        }
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


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        switch (position) {
            case 0:
                mFragmentList.remove (0);
                mFragmentList.add(0, (CategorieView) createdFragment);
                break;
            case 1:
                mFragmentList.remove (1);
                mFragmentList.add(1, (ArticleView) createdFragment);
                break;
            case 2:
                mFragmentList.remove (2);
                mFragmentList.add(2, (PromotionView) createdFragment);
                break;
        }
        return createdFragment;
    }
}


