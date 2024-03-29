package fr.sheol.overlay.drawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import fr.sheol.overlay.drawer.app.HomeFragment;
import fr.sheol.overlay.drawer.app.SettingFragment;
import fr.sheol.overlay.drawer.app.ViewPagerAdapter;

/**
 * Created by Sheol on 14/02/2017.
 */
public class MainActivity extends AppCompatActivity {
    public static final int PERM_REQUEST_CODE_DRAW_OVERLAYS = 42457;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tableLayout);
        if (toolbar != null && viewPager != null && tabLayout != null) {
            setSupportActionBar(toolbar);
            initViewPager();
            actionBar = getSupportActionBar();
            if (actionBar != null) {
                initActionBar();
            }
        }
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(getString(R.string.home), new HomeFragment());
        adapter.addFragment(getString(R.string.settings), new SettingFragment());
        adapter.addFragment(getString(R.string.about), new HomeFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initActionBar() {
        actionBar.setHomeAsUpIndicator(R.drawable.ic_notification);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> DataReceiver.sendRemoteOpen(getBaseContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionToDrawOverlays();
        new ServiceLoader(getApplicationContext()).start();
        DataReceiver.sendActivityEnableEvent(getBaseContext());
    }

    public void permissionToDrawOverlays() {
        if (android.os.Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, PERM_REQUEST_CODE_DRAW_OVERLAYS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (android.os.Build.VERSION.SDK_INT >= 23 && requestCode == PERM_REQUEST_CODE_DRAW_OVERLAYS) {
            new ServiceLoader(getBaseContext()).start();
            if (!Settings.canDrawOverlays(this)) {
                // TODO: 16/02/2017 show windows to explain why overlay perm do enabled
            } else {
                DataReceiver.sendOverlayEnableEvent(getBaseContext());
            }
        }
    }
}
