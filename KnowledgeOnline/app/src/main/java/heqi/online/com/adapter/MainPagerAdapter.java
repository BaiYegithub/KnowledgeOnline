package heqi.online.com.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


/**
 * Created by ytt on 2018/5/9.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    private String[] title;

    public MainPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }


    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment == null ? 0 : listFragment.size();
    }

    public void setStringTitle(String[] title) {
        this.title = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (title != null) {
            return title[position];
        }
        return super.getPageTitle(position);
    }
}
