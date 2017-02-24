package fr.sheol.overlay.drawer.service.drawer.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import fr.sheol.overlay.drawer.R;

/**
 * Created by teddy on 24/02/2017.
 */
public class ImageGrid extends BaseAdapter {
    private List<AppObj> appObjList;

    public ImageGrid(List<AppObj> appObjList) {
        this.appObjList = appObjList;
    }

    @Override
    public int getCount() {
        return appObjList.size();
    }

    @Override
    public AppObj getItem(int position) {
        return appObjList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View grid;
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = inflater.inflate(R.layout.app_list, parent, false);
        } else {
            grid = convertView;
        }
        ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image_app);
        imageView.setImageDrawable(getItem(position).getIcon());
        return grid;
    }
}
