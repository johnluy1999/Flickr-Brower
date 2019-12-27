package vn.edu.usth.flickr;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    //UI
    private ListView listView;
    private ProgressBar progressBar;
    //data
    public static ArrayList<FlickrModel> arrImage = new ArrayList<>();
    private Adapter adapter;
    //current page
    private int currentPage = 1;
    //total page
    private int totalPage = 21067; //perpage 10

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findView
        listView = (ListView) findViewById(R.id.listview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //handle event
        listView.setOnItemClickListener(this);
        //load data from server
        Log.v("Me here","right here");
        getData();
    }

    private void getData() {
        //show data from server
        progressBar.setVisibility(View.VISIBLE);
        //get data from URL
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                //get data from URL
                String url = "https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=f3a8bb4da8ede69dd46edd0e55ccbd10&text=cat&per_page=10&page="+currentPage+"&format=rest";
                String result = Util.getDataFromURL(url);
                Message msg = new Message();
                msg.obj = result;
                //send msg to handler
                networkingHandler.sendMessage(msg);
            }
        };
        thread.start();
    }
    //Handler----------------------------------------------------------------------------------
    Handler networkingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = msg.obj.toString();
            //hide progress bar
            progressBar.setVisibility(View.GONE);
            //get arrayList Image
            ArrayList<FlickrModel> arr = parserData(result);
            //Update total country
            arrImage.addAll(arr);
            if(adapter == null) {
                adapter = new Adapter(MainActivity.this, arrImage);
                listView.setAdapter(adapter);
            }else{
                adapter.notifyDataSetChanged();
            }
            listView.setOnScrollListener(MainActivity.this);
        }
    };

    //Parser data----------------------------------------------------------------------------
    private ArrayList<FlickrModel> parserData(String result) {
        ArrayList<FlickrModel> arr = new ArrayList<>();
        try{
            JSONObject photos = new JSONObject(result);
            JSONArray photo = photos.getJSONArray("photo");
            for(int i = 0;i < photo.length();i++){
                JSONObject image = (JSONObject) photo.get(i);
                FlickrModel flickrModel = new FlickrModel();
                flickrModel.id = image.getString("id");
                flickrModel.secret = image.getString("secret");
                flickrModel.server = image.getString("server");
                flickrModel.farm = image.getString("farm");
                flickrModel.title = image.getString("title");
                //add to array
                arr.add(flickrModel);
            }
            return arr;
        }
        catch (Exception ex) {
            return null;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, Picture.class);
        intent.putExtra("index",position);
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (view.getId() == listView.getId()){
            final int lastItem = firstVisibleItem + visibleItemCount;
            if (lastItem >= totalItemCount){
                this.listView.setOnScrollListener(null);
                //set next page
                if(currentPage < totalPage){
                    currentPage += 1;
                    //request a new data
                    getData();
                }
            }
        }
    }
}
