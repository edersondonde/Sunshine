package br.com.edonde.sunshine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private ShareActionProvider shareProvider;
    private String forecast;
    private final String HASHTAG = "#SunshineApp";

    public DetailFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_share);
        shareProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Log.v(LOG_TAG, "Share provider: "+ shareProvider);
        if (shareProvider != null) {
            Intent intent = createShareForecastIntent();
            this.shareProvider.setShareIntent(intent);
        } else {
            Log.d(LOG_TAG, "Share action provider is null");
        }
    }

    @NonNull
    private Intent createShareForecastIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, forecast + " " + HASHTAG);
        return intent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            forecast = intent.getStringExtra(Intent.EXTRA_TEXT);
            TextView detailTextView = (TextView) rootView.findViewById(R.id.detail_text);
            detailTextView.setText(forecast);
        }

        return rootView;
    }

}
