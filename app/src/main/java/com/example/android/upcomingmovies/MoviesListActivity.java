package com.example.android.upcomingmovies;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.upcomingmovies.Model.Movie;
import com.example.android.upcomingmovies.Model.MoviesResponse;
import com.example.android.upcomingmovies.Rest.ApiClient;
import com.example.android.upcomingmovies.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesListActivity extends AppCompatActivity implements ItemClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private RecyclerView mRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private List<Movie> mMovieList;
    private ProgressDialog mProgressDialog;

    private static final String TAG = MoviesListActivity.class.getSimpleName();
    private final static String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (checkConnection() == true) {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getUpComingMovies(API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    int statusCode = response.code();
                    mMovieList = response.body().getResults();
                    mMoviesAdapter = new MoviesAdapter((getApplicationContext()), mMovieList);
                    mMoviesAdapter.setClickListener(MoviesListActivity.this);
                    mRecyclerView.setAdapter(mMoviesAdapter);
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                    mProgressDialog.dismiss();
                    Toast.makeText(MoviesListActivity.this, "Error Occurred!!",
                            Toast.LENGTH_LONG).show();
                }
            });
        } else {
            mProgressDialog.dismiss();
        }

    }

    @Override
    public void onClick(View view, int position) {
        Movie movie = mMovieList.get(position);

        Intent intent = new Intent(getBaseContext(), MovieDescriptionActivity.class);
        intent.putExtra("MOVIE", movie);
        startActivity(intent);

    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected == false) {
            Toast.makeText(this, "Check your internet connection!!",
                    Toast.LENGTH_LONG).show();
        }
        return isConnected;
    }

    @Override
    protected void onResume() {
        super.onResume();

        MovieApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected == false) {
            Toast.makeText(this, "Check your internet connection!!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_info:
                final AlertDialog alertDialog = new AlertDialog.Builder(
                        MoviesListActivity.this).create();

                alertDialog.setTitle("Alert Dialog");
                alertDialog.setMessage("Developed by Sourabh Jinde (sourabhjinde@gmail.com) :)");
                alertDialog.setIcon(android.R.drawable.ic_dialog_info);
                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
            default:
                break;
        }
        return true;
    }
}
