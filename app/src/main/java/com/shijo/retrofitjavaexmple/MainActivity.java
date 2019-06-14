package com.shijo.retrofitjavaexmple;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    AdapterGridAlbums mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, dpToPx(this, 4), true));
        recyclerView.setHasFixedSize(true);

        AlbumService albumService = RetrofitInstance.getService();

        Call<List<Photo>> albums= albumService.getPhotos();

        albums.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Log.d("shijo", "shijo");
                List<Photo> photos = response.body();
                mAdapter = new AdapterGridAlbums(MainActivity.this, photos);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d("shijo", "shijo");
            }
        });
    }

    public static int dpToPx(Context c, int dp) {
        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
