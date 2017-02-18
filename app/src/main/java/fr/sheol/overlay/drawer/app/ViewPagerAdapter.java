package fr.sheol.overlay.drawer.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.preference.PreferenceFragment;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sheol on 18/02/2017.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Map<String, Object>> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Object fragment = fragments.get(position).get("fragment");
        if (fragment instanceof PreferenceFragment) {
            return (PreferenceFragment) fragment;
        }
        return (Fragment) fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) fragments.get(position).get("title");
    }

    public void addFragment(String title, Object fragment) {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("title", title);
        tmp.put("fragment", fragment);
        fragments.add(tmp);
    }
}
