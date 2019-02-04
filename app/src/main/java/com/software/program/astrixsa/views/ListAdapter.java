package com.software.program.astrixsa.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.software.program.astrixsa.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.software.program.astrixsa.R.layout.item_list;
/**
 * Created by user on 29/11/2018.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private Context context;
    private Activity activity;
    private String[] nameVideos, descriptions;
    private String[] images;

    public ListAdapter(Activity activity, String[] images, String[] descriptions, String[] nameVideos) {
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
        //folder.imageVideo.setImageResource(images[index]);
        folder.imageVideo.setImageBitmap(getBitmap(images[index]));
        folder.nameVideo.setText(nameVideos[index]);
        folder.nameDescription.setText(descriptions[index]);
        return view1;
    }
    private Bitmap getBitmap(String url){
        String nombreFoto = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +"/astrix/"+url;
        Uri output = Uri.fromFile(new File(nombreFoto));
        InputStream is;
        Bitmap bitmap = null;
        try {
            is = activity.getContentResolver().openInputStream(output);
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);

        } catch (FileNotFoundException ignored) {
            System.out.println("asdasdasd");
        }
        return bitmap;
    }
    class ViewModel {
        ImageView imageVideo;
        TextView nameVideo, nameDescription;

        ViewModel(View view) {
            imageVideo = view.findViewById(R.id.nameimage);
            nameDescription = view.findViewById(R.id.namedescription);
            nameVideo = view.findViewById(R.id.namevideo);
        }
    }
}
