package haejung.com.fridge.ui;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import haejung.com.fridge.ui.base.FragmentInteractionListener;
import haejung.com.fridge.R;
import haejung.com.fridge.ui.list.ColdStorageFragment;
import haejung.com.fridge.ui.list.EtcStorageFragment;
import haejung.com.fridge.ui.list.FrozenStorageFragment;
import haejung.com.fridge.ui.list.KeepFragment;


public class MainActivity extends AppCompatActivity implements FragmentInteractionListener {
    private static String TAG = MainActivity.class.getSimpleName();

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Slide());
        }

        setContentView(R.layout.activity_main);

        bindings();

        setupViews();
    }

    private void bindings() {
        viewPager = (ViewPager) findViewById(R.id.pagers);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    private void setupViews() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(0, ColdStorageFragment.newInstance(null, null));
        fragmentList.add(1, FrozenStorageFragment.newInstance(null, null));
        fragmentList.add(2, EtcStorageFragment.newInstance(null, null));
        fragmentList.add(3, KeepFragment.newInstance(null, null));

        FragmentPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction() {

    }

    private class MainViewPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;

        public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String[] pageTitles = getResources().getStringArray(R.array.tabs);
            return pageTitles[position];
        }
    }
}
