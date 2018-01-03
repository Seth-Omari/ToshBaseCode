/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.view.autocomplete;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;

import ke.co.toshngure.basecode.R;

/**
 * Created by Anthony Ngure on 03/01/2018.
 * Email : anthonyngure25@gmail.com.
 */

public class FilterableListView extends FrameLayout {

    private EditText mEditText;
    private ListView mListView;
    private FilterAdapterAdapter adapter;

    public FilterableListView(@NonNull Context context) {
        this(context, null);
    }

    public FilterableListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterableListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        LayoutInflater.from(getContext()).inflate(R.layout.view_filterable_list_view, this, true);
        mEditText = findViewById(R.id.editText);
        mListView = findViewById(R.id.listView);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FilterableListView);

        String hint = typedArray.getString(R.styleable.FilterableListView_flvHint);
        if (!TextUtils.isEmpty(hint)) {
            mEditText.setHint(hint);
        }

        typedArray.recycle();

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // When user changed the Text
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setFilterAdapterConfig(FilterAdapterConfig filterAdapterConfig) {
        adapter = filterAdapterConfig.build(mListView);
        mListView.setAdapter(adapter);
    }

}
