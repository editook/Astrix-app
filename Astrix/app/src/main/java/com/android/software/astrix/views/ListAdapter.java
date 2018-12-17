package com.android.software.astrix.views;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.software.astrix.R;

import static com.android.software.astrix.R.layout.item_list;

/**
 * Created by user on 29/11/2018.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private Context context;
    private Activity activity;
    private String[] nameVideos, descriptions;
    private Integer[] images;

    public ListAdapter(Activity activity, Integer[] images, String[] descriptions, String[] nameVideos) {
        super(activity, item_list, nameVideos);
        this.activity = activity;
        this.nameVideos = nameVideos;
        this.images = images;
        this.descriptions = descriptions;
        context = activity.getBaseContext();
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        View view1 = view;
        ViewModel folder = null;
        if (view1 == null) {
            LayoutInflater inf = activity.getLayoutInflater();
            view1 = inf.inflate(item_list, null, true);
            folder = new ViewModel(view1);
            view1.setTag(folder);
        } else {
            folder = (ViewModel) view1.getTag();
        }
        folder.imageVideo.setImageResource(images[index]);
        folder.nameVideo.setText(nameVideos[index]);
        folder.nameDescription.setText(descriptions[index]);
        return view1;
    }

    class ViewModel {
        ImageView imageVideo;
        TextView nameVideo, nameDescription;

        ViewModel(View view) {
            imageVideo = (ImageView) view.findViewById(R.id.nameimage);
            nameDescription = (TextView) view.findViewById(R.id.namedescription);
            nameVideo = (TextView) view.findViewById(R.id.namevideo);
        }
    }
}
