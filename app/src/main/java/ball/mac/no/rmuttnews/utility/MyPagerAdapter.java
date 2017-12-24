package ball.mac.no.rmuttnews.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ball.mac.no.rmuttnews.fragment.FollowerFragment;
import ball.mac.no.rmuttnews.fragment.MapsFragment;
import ball.mac.no.rmuttnews.fragment.NewsFragment;
import ball.mac.no.rmuttnews.fragment.NotificationFragment;
import ball.mac.no.rmuttnews.fragment.SearchFragment;

/**
 * Created by SB Dino on 23-Dec-17.
 */

public class MyPagerAdapter extends FragmentPagerAdapter{


    private FragmentManager fragmentManager;
    private int anInt;

    public MyPagerAdapter(FragmentManager fragmentManager, int anInt) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.anInt = anInt;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FollowerFragment followerFragment = new FollowerFragment();
                return followerFragment;
            case 1:
                NotificationFragment notificationFragment = new NotificationFragment();
                return notificationFragment;
            case 2:
                NewsFragment newsFragment = new NewsFragment();
                return  newsFragment;
            case 3:
                MapsFragment mapsFragment = new MapsFragment();
                return mapsFragment;
            case 4:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;
            default: return null;

        }

    }

    @Override
    public int getCount() {
        return anInt;
    }
}//Main Class
