package fr.sheol.overlay.drawer.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by teddy on 20/02/2017.
 */
@Entity
public class Trigger {
    @Id
    private int id;

    @NotNull
    private long start;
    private long end;
    private boolean enabled;

    @Generated(hash = 119499893)
    public Trigger(int id, long start, long end, boolean enabled) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.enabled = enabled;
    }

    @Generated(hash = 533915093)
    public Trigger() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStart() {
        return this.start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return this.end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
