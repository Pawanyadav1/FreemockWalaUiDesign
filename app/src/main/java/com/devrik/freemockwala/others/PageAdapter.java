package com.devrik.freemockwala.others;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.devrik.freemockwala.Fragment.frag1;
import com.devrik.freemockwala.Fragment.frag2;
import com.devrik.freemockwala.Fragment.frag3;

public class PageAdapter extends FragmentPagerAdapter
{

    int Tabcount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        Tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0: return new frag1();
            case 1: return new frag2();
            case 2: return new frag3();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return Tabcount;
    }
}
