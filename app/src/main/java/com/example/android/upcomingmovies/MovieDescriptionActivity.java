package com.example.android.upcomingmovies;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.android.upcomingmovies.Model.Movie;
import com.example.android.upcomingmovies.Model.MovieImages;
import com.example.android.upcomingmovies.Model.MovieImagesResponse;
import com.example.android.upcomingmovies.Rest.ApiClient;
import com.example.android.upcomingmovies.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDescriptionActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private SliderLayout mSliderLayout;
    private TextView title;
    private TextView overview;
    private RatingBar movieRating;
    private Movie movie;
    private ProgressDialog progressDialog;

    private static final String TAG = MovieDescriptionActivity.class.getSimpleName();
    private final static String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
        movieRating = (RatingBar) findViewById(R.id.movieRating);
        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        movie = (Movie) getIntent().getSerializableExtra("MOVIE");

        title.setText(movie.getmTitle());
        overview.setText(movie.getmOverview());
        movieRating.setRating((float) (movie.getmPopularity() * 5.0 / 100.0));

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        if (checkConnection() == true) {
            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MovieImagesResponse> call = apiService.getMovieDetails(movie.getId(), API_KEY);
            call.enqueue(new Callback<MovieImagesResponse>() {
                @Override
                public void onResponse(Call<MovieImagesResponse> call, Response<MovieImagesResponse> response) {
                    progressDialog.dismiss();
                    int statusCode = response.code();
                    List<MovieImages> movies = response.body().getMovieImagesList();
                    for (MovieImages movieImages : movies) {
                        String path = "http://image.tmdb.org/t/p/w500/" + movieImages.getFilePath();

                        TextSliderView textSliderView = new TextSliderView(MovieDescriptionActivity.this);
                        // initialize a SliderLayout
                        textSliderView
                                .description("description")
                                .image(path)
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(MovieDescriptionActivity.this);

                        mSliderLayout.addSlider(textSliderView);
                    }
                    mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                    mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                    mSliderLayout.setCustomAnimation(new DescriptionAnimation());
                    mSliderLayout.setDuration(4000);
                    mSliderLayout.addOnPageChangeListener(MovieDescriptionActivity.this);

                }

                @Override
                public void onFailure(Call<MovieImagesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                    progressDialog.dismiss();
                    Toast.makeText(MovieDescriptionActivity.this, "Error Occured!!",
                            Toast.LENGTH_LONG).show();

                }
            });
        } else {
            progressDialog.dismiss();
        }
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
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
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
