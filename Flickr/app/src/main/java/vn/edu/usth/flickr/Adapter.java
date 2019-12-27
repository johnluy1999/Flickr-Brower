package vn.edu.usth.flickr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<FlickrModel> arr;
    public Adapter(Context context, ArrayList<FlickrModel> arr){
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    @Override
    public Object getItem(int position) {
        return this.arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View rowview = inflater.inflate(R.layout.item,parent,false);

        //get
        TextView textitem = (TextView) rowview.findViewById(R.id.textitem);
        ImageView imageView = (ImageView) rowview.findViewById(R.id.itemView);
        //set title
        textitem.setText(this.arr.get(position).title);
        //set image
        final String urlImage = "https://farm"+arr.get(position).farm +".staticflickr.com/"+arr.get(position).server+"/"+arr.get(position).id+"_"+arr.get(position).secret+"_s.jpg";
        /*
                        https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
                        s	hình vuông nhỏ 75x75
                        q	large square 150x150
                        t	ảnh thu nhỏ, cạnh dài nhất là 100
                        m	nhỏ, cạnh dài nhất là 240
                        n	small, 320 on longest side
                        -	trung bình, cạnh dài nhất là 500
                        z	trung bình 640, 640 trên cạnh dài nhất
                        c	trung bình 800, 800 trên cạnh dài nhất†
                        b	lớn, 1024 trên cạnh dài nhất*
                        h	lớn 1600, 1600 ở cạnh dài nhất†
                        k	lớn 2048, 2048 ở cạnh dài nhất†
                        o	ảnh gốc, định dạng jpg, gif hoặc png, tùy vào định dạng nguồn
         */
        Util.setBitmapToImage(urlImage,imageView);
        return rowview;
    }

}
