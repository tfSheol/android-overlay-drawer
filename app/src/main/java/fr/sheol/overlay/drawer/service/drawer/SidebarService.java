package fr.sheol.overlay.drawer.service.drawer;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;

import fr.sheol.overlay.drawer.DataReceiver;
import fr.sheol.overlay.drawer.R;
import fr.sheol.overlay.drawer.ServiceLoader;

/**
 * Created by Sheol on 14/02/2017.
 */
public class SidebarService extends Service implements DrawerLayout.DrawerListener,
        View.OnClickListener, View.OnTouchListener, CustomDrawerLayout.DrawerKeyListener,
        EventDrawerReceiver.DrawerReceiverListener, AppData.ApplicationListener {
    public static final int FLAG_NULL = 0x00000000;
    public static final int NOTIFICATION_BACKGROUND_ID = 4367;
    private static final int overlaySize = 10;
    private WindowManager windowManager;
    private CustomDrawerLayout drawerLayout;
    private EventDrawerReceiver eventDrawerReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        final LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        drawerLayout = (CustomDrawerLayout) inflater.inflate(R.layout.drawer_overlay, null);
        drawerLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        drawerLayout.addDrawerListener(this);
        drawerLayout.findViewById(R.id.drawer_panel).setOnClickListener(this);
        drawerLayout.setOnTouchListener(this);
        drawerLayout.setOnDispatchKeyEventPreIme(this);
        new AppData(getBaseContext(), (GridView) drawerLayout.findViewById(R.id.left_drawer), this);
        if (ServiceLoader.checkOverlayPerm(this)) {
            windowManager.addView(drawerLayout, getParams(overlaySize,
                    WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE));
            eventDrawerReceiver = new EventDrawerReceiver();
            eventDrawerReceiver.setDrawerReceiverListener(this);
            runningInBackground();
        } else {
            stopService(new Intent(this, SidebarService.class));
        }
    }

    private void runningInBackground() {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getBaseContext());
        notification.setSmallIcon(R.drawable.ic_notification);
        // TODO: 24/02/2017 add more information about current running service in a notification 
        notification.setPriority(Notification.PRIORITY_MIN);
        startForeground(NOTIFICATION_BACKGROUND_ID, notification.build());
        registerReceiver();
    }

    private void registerReceiver() {
        getBaseContext().registerReceiver(eventDrawerReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        getBaseContext().registerReceiver(eventDrawerReceiver, new IntentFilter(DataReceiver.REMOTE_OPEN));
    }

    private void unregisterReceiver() {
        getBaseContext().unregisterReceiver(eventDrawerReceiver);
    }

    @Override
    public void onDestroy() {
        unregisterReceiver();
        super.onDestroy();
    }

    private void openLayoutDrawer(WindowManager windowManager, DrawerLayout drawerLayout) {
        windowManager.updateViewLayout(drawerLayout, getParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT, FLAG_NULL));
    }

    private void closeLayoutDrawer(WindowManager windowManager, DrawerLayout drawerLayout) {
        windowManager.updateViewLayout(drawerLayout, getParams(overlaySize,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE));
    }

    private WindowManager.LayoutParams getParams(int width, int height, int focus) {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                width, height,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                focus | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.START;
        return params;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        closeLayoutDrawer(windowManager, drawerLayout);
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        switch (newState) {
            case DrawerLayout.STATE_DRAGGING:
                openLayoutDrawer(windowManager, drawerLayout);
                break;
            case DrawerLayout.STATE_IDLE:
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    closeLayoutDrawer(windowManager, drawerLayout);
                }
                break;
            case DrawerLayout.STATE_SETTLING:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // TODO: 16/02/2017 add interface listener for catch click event in other layout
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return v.onTouchEvent(event);
    }

    @Override
    public boolean onDispatchKeyEventPreIme(boolean initialState, KeyEvent event) {
        // TODO: 16/02/2017 back event in drawer only + close drawer on a first layout
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return initialState;
    }

        @Override
        public void onHomeKeyListener() {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        @Override
        public void onRemoteOpen() {
            openLayoutDrawer(windowManager, drawerLayout);
            new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onApplicationLaunched() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }
}