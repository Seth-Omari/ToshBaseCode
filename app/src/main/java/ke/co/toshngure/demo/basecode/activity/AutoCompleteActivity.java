/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.demo.basecode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import ke.co.toshngure.basecode.utils.BaseUtils;
import ke.co.toshngure.basecode.view.autocomplete.AutoCompleteView;
import ke.co.toshngure.basecode.view.autocomplete.FilterAdapterConfig;
import ke.co.toshngure.demo.basecode.R;
import ke.co.toshngure.demo.basecode.model.Post;

public class AutoCompleteActivity extends BaseActivity {

    private static final String EXTRA_FROM_NETWORK = "extra_from_network";
    @BindView(R.id.textACV)
    AutoCompleteView textACV;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindArray(R.array.counties)
    String[] counties;

    public static void start(Context context, boolean fromNetwork) {
        Intent starter = new Intent(context, AutoCompleteActivity.class);
        starter.putExtra(EXTRA_FROM_NETWORK, fromNetwork);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textACV.setLoadingIndicator(progressBar);

        FilterAdapterConfig<String> countiesFilterAdapterConfig = new FilterAdapterConfig<String>(this)
                .setAutocompleteSource(Arrays.asList(counties), String::contains)
                .setSelectionListener(this::toastDebug)
                .setBinders(new AutoCompleteView.Binder<String>() {
                    @Override
                    public void bind(View convertView, String item) {
                        ((TextView) convertView.findViewById(android.R.id.text1)).setText(item);
                    }

                    @Override
                    public int getRowLayout() {
                        return android.R.layout.simple_dropdown_item_1line;
                    }

                    @Override
                    public String getItemTextValue(String item) {
                        return item;
                    }
                });

        FilterAdapterConfig<Post> postsFilterAdapterConfig = new FilterAdapterConfig<Post>(this)
                .setAutocompleteSource("https://jsonplaceholder.typicode.com/posts?_limit=20?query=")
                .setBinders(new AutoCompleteView.Binder<Post>() {
                    @Override
                    public void bind(View convertView, Post item) {
                        ((TextView) convertView.findViewById(R.id.titleTV)).setText(item.getTitle());
                        ((TextView) convertView.findViewById(R.id.bodyTV)).setText(item.getBody());
                    }

                    @Override
                    public int getRowLayout() {
                        return R.layout.cell_post;
                    }

                    @Override
                    public String getItemTextValue(Post item) {
                        return item.getTitle();
                    }
                })
                .setParser(response -> {
                    List<Post> postList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            postList.add(BaseUtils.getSafeGson().fromJson(jsonArray.getJSONObject(i).toString(), Post.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return postList;
                }).setSelectionListener(item -> toastDebug(item.getTitle()));

        boolean fromNetwork = getIntent().getBooleanExtra(EXTRA_FROM_NETWORK, false);
        textACV.setFilterAdapterConfig(fromNetwork ? postsFilterAdapterConfig : countiesFilterAdapterConfig);

    }

}
