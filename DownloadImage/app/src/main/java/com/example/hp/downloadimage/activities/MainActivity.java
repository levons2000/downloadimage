package com.example.hp.downloadimage.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.downloadimage.R;
import com.example.hp.downloadimage.adapters.RecyclerAdapter;
import com.example.hp.downloadimage.fragments.MyDialogFragment;
import com.example.hp.downloadimage.models.RecyclerModel;
import com.example.hp.downloadimage.utils.DataProvider;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton imageButton;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        findViews();
        addItems();
        setRecycler();
        setListenerForDownload();
    }

    private void findViews() {
        recyclerView = findViewById(R.id.images_recyc);
        imageButton = findViewById(R.id.download_image);
        textView = findViewById(R.id.link_text);

    }

    private void setRecycler() {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerAdapter);

    }

    private void addItems() {
        DataProvider.list.add(new RecyclerModel("https://media.istockphoto.com/photos/plant-growing-picture-id510222832?k=6&m=510222832&s=612x612&w=0&h=Pzjkj2hf9IZiLAiXcgVE1FbCNFVmKzhdcT98dcHSdSk=", "Image 1", false));
        DataProvider.list.add(new RecyclerModel("https://images.pexels.com/photos/34950/pexels-photo.jpg?auto=compress&cs=tinysrgb&h=350", "Image 2", false));
        DataProvider.list.add(new RecyclerModel("https://www.w3schools.com/w3css/img_lights.jpg", "Image 3", false));
        DataProvider.list.add(new RecyclerModel("https://www.w3schools.com/w3images/fjords.jpg", "Image 4", false));
        DataProvider.list.add(new RecyclerModel("https://www.elastic.co/assets/bltada7771f270d08f6/enhanced-buzz-1492-1379411828-15.jpg", "Image 5", false));
    }

    private void setListenerForDownload() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!textView.getText().equals("")) {
                    if (!DataProvider.getItemByPos().isDownloaded) {
                        DataProvider.getItemByPos().isDownloaded = true;
                        MyAsynchTask myAsynchTask = new MyAsynchTask();
                        myAsynchTask.setContext(MainActivity.this);
                        try {
                            myAsynchTask.execute(new URL(DataProvider.getItemByPos().getImgUrl()));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    } else {
                        MyDialogFragment dialogFragment = new MyDialogFragment();
                        dialogFragment.show(getSupportFragmentManager(), "tag");
                    }
                }
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class MyAsynchTask extends AsyncTask<URL, Void, String> {
        MainActivity mainActivity;

        public void setContext(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(mainActivity, "Download Started", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(URL... urls) {
            int count;
            try {
                URL url = urls[0];
                URLConnection conection = url.openConnection();
                conection.connect();
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);
                OutputStream output = new FileOutputStream(Environment.getRootDirectory().getAbsolutePath()
                        + DataProvider.getItemByPos().getName());
                byte data[] = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(mainActivity, "Image Is Downloaded", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) +
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (MainActivity.this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission
                                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CONTACTS},
                        1001);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_CONTACTS
                        },
                        1001);
            }
        }
    }
}
