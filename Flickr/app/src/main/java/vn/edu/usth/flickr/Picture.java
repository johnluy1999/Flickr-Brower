package vn.edu.usth.flickr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Picture extends AppCompatActivity {
    private FlickrModel flickrModel;
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        //get index
        int index = getIntent().getExtras().getInt("index");
        //get FlickrModel
        flickrModel = MainActivity.arrImage.get(index);
        //find view
        picture = (ImageView) findViewById(R.id.picture);
        //set Picture
        final String urlImage = "https://farm"+MainActivity.arrImage.get(index).farm +".staticflickr.com/"+MainActivity.arrImage.get(index).server+"/"+MainActivity.arrImage.get(index).id+"_"+MainActivity.arrImage.get(index).secret+"_q.jpg";
        Util.setBitmapToImage(urlImage,picture);
    }
}
