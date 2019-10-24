package com.proclassmates.ganandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.proclassmates.ganandroid.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final String SEARCH_QUERY_URL_EXTRA = "query";

    /*
     * This number will uniquely identify our Loader and is chosen arbitrarily. You can change this
     * to any number you like, as long as you use the same variable name.
     */
    private static final int GITHUB_SEARCH_LOADER = 22;

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        Bundle queryBundle= null;
        if (savedInstanceState != null) {
            mUrlDisplayTextView.setText(savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA));

            queryBundle = new Bundle();
            queryBundle.putString(SEARCH_QUERY_URL_EXTRA, savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA));
        }

        getSupportLoaderManager().initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeGithubSearchQuery() {
        String query = mSearchBoxEditText.getText().toString();

        if (query.isEmpty()) {
            mUrlDisplayTextView.setText("input query is empty!");
            return;
        }

        URL url = NetworkUtils.buildUrl(query);
        mUrlDisplayTextView.setText(url.toString());

        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, url.toString());

        LoaderManager loaderManager = getSupportLoaderManager();

        Loader<String> queryLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);

        if (queryLoader != null) {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        } else {

            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }


    }

    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String mGithubJson;

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (args == null) {
                    return;
                }

                if (mGithubJson != null) {
                    deliverResult(mGithubJson);
                } else {
                    mLoadingIndicator.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {
                /* Extract the search query from the args using our constant */
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);

                /* If the user didn't enter anything, there's nothing to search for */
                if (TextUtils.isEmpty(searchQueryUrlString)) {
                    return null;
                }

                /* Parse the URL from the passed in String and perform the search */
                try {
                    URL githubUrl = new URL(searchQueryUrlString);
                    String githubSearchResults = NetworkUtils.getResponseFromHttpUrl(githubUrl);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(@Nullable String data) {
                super.deliverResult(data);
            }


        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        /* When we finish loading, we want to hide the loading indicator from the user. */
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        /*
         * If the results are null, we assume an error has occurred. There are much more robust
         * methods for checking errors, but we wanted to keep this particular example simple.
         */
        if (null == data) {
            showErrorMessage();
        } else {
            mSearchResultsTextView.setText(data);
            showJsonDataView();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String queryUrl = mUrlDisplayTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, queryUrl);
    }
}
