/*
 * Copyright (c) 2018.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.view.autocomplete;

import android.content.Context;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Anthony Ngure on 03/01/2018.
 * Email : anthonyngure25@gmail.com.
 */

public class FilterAdapterConfig<M> {

    public static final int DEFAULT_THRESHOLD = 3;

    private FilterAdapterAdapter<M> adapter;
    private AutoCompleteView.SelectionListener<M> selectionListener;

    public FilterAdapterConfig(Context context) {
        adapter = new FilterAdapterAdapter<>(context);
    }

    public FilterAdapterConfig<M> setSelectionListener(AutoCompleteView.SelectionListener<M> selectionListener) {
        this.selectionListener = selectionListener;
        return this;
    }

    public FilterAdapterConfig<M> setAutocompleteSource(String autocompleteUrl) {
        adapter.setAutocompleteSource(autocompleteUrl);
        return this;
    }

    public FilterAdapterConfig<M> setAutocompleteSource(List<M> autocompleteList, AutoCompleteView.Matcher<M> matcher) {
        adapter.setAutocompleteSource(autocompleteList, matcher);
        return this;
    }

    public FilterAdapterConfig<M> setParser(AutoCompleteView.Parser<M> parser) {
        adapter.setParser(parser);
        return this;
    }


    public FilterAdapterConfig<M> setBinders(AutoCompleteView.Binder<M> binder) {
        adapter.setBinders(binder);
        return this;
    }


    FilterAdapterAdapter<M> build(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, itemId) ->
                this.selectionListener.onItemSelected(this.adapter.getResultList().get(position)));
        return adapter;
    }

    FilterAdapterAdapter<M> build(ListView listView) {
        listView.setOnItemClickListener((adapterView, view, position, itemId) ->
                this.selectionListener.onItemSelected(this.adapter.getResultList().get(position)));
        return adapter;
    }
}
