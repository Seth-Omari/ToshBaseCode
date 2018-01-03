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
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by Anthony Ngure on 6/24/15.
 * Email : anthonyngure25@gmail.com.
 */
public class AutoCompleteView extends android.support.v7.widget.AppCompatAutoCompleteTextView {
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            AutoCompleteView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };
    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;
    private AutoCompleteAdapter mAdapter;
    private View mLoadingIndicator;
    private SelectionListener mSelectionListener;

    public AutoCompleteView(Context context) {
        super(context);
        setUpAdapter();
    }

    public AutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpAdapter();
    }

    public AutoCompleteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpAdapter();
    }


    public void setSelectionListener(SelectionListener selectionListener) {
        this.mSelectionListener = selectionListener;
        this.setOnItemClickListener((adapterView, view, position, id) ->
                mSelectionListener.onItemSelected(mAdapter.getItem(position)));

    }

    public void setAutocompleteUrl(String mAutocompleteUrl) {
        mAdapter.setAutocompleteUrl(mAutocompleteUrl);
    }

    public void setParser(Parser parser) {
        mAdapter.setParser(parser);
    }

    public void setLoadingIndicator(View loadingIndicator) {
        this.mLoadingIndicator = loadingIndicator;
    }

    public void setAutoCompleteDelay(int autoCompleteDelay) {
        mAutoCompleteDelay = autoCompleteDelay;
    }

    private void setUpAdapter() {
        mAdapter = new AutoCompleteAdapter(getContext());
        setAdapter(mAdapter);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        if (mLoadingIndicator != null) mLoadingIndicator.setVisibility(View.VISIBLE);
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TEXT_CHANGED, text), mAutoCompleteDelay);
    }

    @Override
    public void onFilterComplete(int count) {
        if (mLoadingIndicator != null) mLoadingIndicator.setVisibility(View.GONE);
        super.onFilterComplete(count);
    }

    public interface SelectionListener {
        void onItemSelected(String string);
    }

    public interface Parser {
        List<String> parse(String response);
    }
}
