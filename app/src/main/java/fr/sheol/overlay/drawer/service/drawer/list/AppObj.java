package fr.sheol.overlay.drawer.service.drawer.list;

import android.graphics.drawable.Drawable;

/**
 * Created by teddy on 24/02/2017.
 */
public class AppObj {
    private String name;
    private String packageName;
    private String className;
    private Drawable icon;

    public AppObj(String name, String packageName, String className, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.className = className;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public Drawable getIcon() {
        return icon;
    }
}
