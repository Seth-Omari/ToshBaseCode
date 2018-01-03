/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.view.autocomplete;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ke.co.toshngure.basecode.log.BeeLog;

/**
 * Created by Anthony Ngure on 6/24/15.
 * Email : anthonyngure25@gmail.com.
 */
public class FilterAdapterAdapter<T> extends BaseAdapter implements Filterable {

    private static final String TAG = "FilterAdapterAdapter";

    private String mAutocompleteUrl;
    private Context mContext;

    private List<T> mResultList = new ArrayList<>();
    private ArrayList<T> mAutocompleteSource = new ArrayList<>();
    private AutoCompleteView.Parser<T> mParser;
    private AutoCompleteView.Binder<T> mBinder;
    private boolean isLocal;
    private AutoCompleteView.Matcher<T> mMatcher;


    FilterAdapterAdapter(Context context) {
        this.mContext = context;
    }

    private static String connect(String urlString) {
        BeeLog.i(TAG, "URL ==> " + urlString);
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {

            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[512];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }

        } catch (MalformedURLException e) {
            BeeLog.i(TAG, "Error processing Autocomplete API URL");
            BeeLog.e(TAG, e);
        } catch (IOException e) {
            BeeLog.i(TAG, "Error connecting to Autocomplete API");
            BeeLog.e(TAG, e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        BeeLog.i(TAG, "Result " + jsonResults.toString());
        return jsonResults.toString();
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        Object item = mBinder.getItemTextValue(mResultList.get(position));
        return item == null ? mResultList.get(position) : item;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mBinder.getRowLayout(), parent, false);
        }
        mBinder.bind(convertView, mResultList.get(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence != null) {
                    List<T> suggestions = getResponse(charSequence.toString());
                    // Assign the data to the FilterResults
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                if (results != null && results.count > 0) {
                    mResultList = (List<T>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private List<T> getResponse(final String query) {
        if (TextUtils.isEmpty(query) || query.length() < FilterAdapterConfig.DEFAULT_THRESHOLD) {
            return mAutocompleteSource;
        }
        if (isLocal) {
            List<T> matches = new ArrayList<>();
            for (T item : mResultList) {
                if (mMatcher.matches(item, query)) {
                    matches.add(item);
                }
            }
            return matches;
        } else {
            String url = appendInput(query);
            String response = connect(url);
            return mParser.parse(response);
        }
    }

    private String appendInput(String query) {
        return mAutocompleteUrl + Uri.encode(query);
    }

    void setAutocompleteSource(String autocompleteUrl) {
        this.mAutocompleteUrl = autocompleteUrl;
    }

    void setAutocompleteSource(List<T> autocompleteSource, AutoCompleteView.Matcher<T> matcher) {
        this.isLocal = true;
        this.mMatcher = matcher;
        this.mAutocompleteSource.addAll(autocompleteSource);
        this.mResultList.addAll(autocompleteSource);
    }

    void setParser(AutoCompleteView.Parser<T> parser) {
        this.mParser = parser;
    }

    void setBinders(AutoCompleteView.Binder<T> binder) {
        this.mBinder = binder;
    }

    public List<T> getResultList() {
        return mResultList;
    }
}
