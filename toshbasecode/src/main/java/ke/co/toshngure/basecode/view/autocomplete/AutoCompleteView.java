/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.view.autocomplete;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Anthony Ngure on 6/24/15.
 * Email : anthonyngure25@gmail.com.
 */
public class AutoCompleteView extends AppCompatAutoCompleteTextView {
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AutoCompleteView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };
    private View mLoadingIndicator;

    public AutoCompleteView(Context context) {
        super(context);
    }

    public AutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCompleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFilterAdapterConfig(FilterAdapterConfig filterAdapterConfig) {
        this.setAdapter(filterAdapterConfig.build(this));
    }


    public void setLoadingIndicator(View loadingIndicator) {
        this.mLoadingIndicator = loadingIndicator;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        if (mLoadingIndicator != null) mLoadingIndicator.setVisibility(View.VISIBLE);
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TEXT_CHANGED, text), DEFAULT_AUTOCOMPLETE_DELAY);
    }

    @Override
    public void onFilterComplete(int count) {
        if (mLoadingIndicator != null) mLoadingIndicator.setVisibility(View.GONE);
        super.onFilterComplete(count);
    }

    public interface SelectionListener<T> {
        void onItemSelected(T item);
    }

    public interface Binder<T> {
        void bind(View convertView, T item);

        @LayoutRes
        int getRowLayout();

        @Nullable
        String getItemTextValue(T item);
    }

    public interface Parser<T> {
        @NonNull
        List<T> parse(String response);
    }

    public interface Matcher<T> {
        boolean matches(T item, String query);
    }
}
