package fr.sheol.overlay.drawer;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.KeyEvent;

/**
 * Created by Sheol on 16/02/2017.
 */
public class CustomDrawerLayout extends DrawerLayout {
    private DrawerKeyListener drawerKeyListener;

    public CustomDrawerLayout(Context context) {
        super(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnDispatchKeyEventPreIme(DrawerKeyListener drawerKeyListener) {
        this.drawerKeyListener = drawerKeyListener;
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return drawerKeyListener.onDispatchKeyEventPreIme(super.dispatchKeyEventPreIme(event), event);
    }

    interface DrawerKeyListener {
        boolean onDispatchKeyEventPreIme(boolean initialState, KeyEvent event);
    }
}
