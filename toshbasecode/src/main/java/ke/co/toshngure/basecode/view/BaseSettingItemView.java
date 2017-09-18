/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ke.co.toshngure.basecode.R;


/**
 * Created by Anthony Ngure on 7/25/2016.
 * Email : anthonyngure25@gmail.com.
 * Company : VibeCampo Social Network..
 */
public class BaseSettingItemView extends FrameLayout {

    private TextView mValueTextView;
    private TextView mSummaryTextView;
    private ImageView mIconImageView;
    private SwitchCompat mSwitchCompat;
    private SwitchChecked onSwitchChecked;
    private DisplayMode mDisplayMode = DisplayMode.DefaultMode;

    public BaseSettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BaseSettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BaseSettingItemView);

        View mContentView = LayoutInflater.from(getContext()).inflate(R.layout.view_setting_item, this, true);

        mSwitchCompat =  mContentView.findViewById(R.id.switchCompat);
        mValueTextView =  mContentView.findViewById(R.id.itemValue);
        mSummaryTextView =  mContentView.findViewById(R.id.itemSummary);
        mIconImageView =  mContentView.findViewById(R.id.itemDrawable);
        mDisplayMode = DisplayMode.values()[typedArray.getInt(R.styleable.BaseSettingItemView_itemDisplayMode, 0)];

        if (mDisplayMode == DisplayMode.SwitchMode) {
            mSwitchCompat.setVisibility(VISIBLE);
            mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onSwitchChecked != null) {
                        onSwitchChecked.onSwitchChecked(isChecked);
                    }
                }
            });
        } else {
            mSwitchCompat.setVisibility(GONE);
        }

        setItemValue(typedArray.getString(R.styleable.BaseSettingItemView_itemValue));
        setItemSummary(typedArray.getString(R.styleable.BaseSettingItemView_itemSummary));
        setItemDrawable(typedArray.getDrawable(R.styleable.BaseSettingItemView_itemDrawable));

        if (typedArray.getBoolean(R.styleable.BaseSettingItemView_itemHideDivider, false)) {
            mContentView.findViewById(R.id.itemSeparator).setVisibility(GONE);
        }

/*
        int color = typedArray.(R.styleable.BaseSettingItemView_itemValueTextAppearance, Color.WHITE);
        mValueTextView.setTextColor(color);
*/

        typedArray.recycle();
    }

    public void setSwitchCompat(boolean val) {
        mSwitchCompat.setChecked(val);
    }

    public TextView getSummaryTextView() {
        return mSummaryTextView;
    }

    public String getValue() {
        return mValueTextView.getText().toString();
    }

    public void setItemValue(String itemValue) {
        if ((itemValue != null) && (!itemValue.isEmpty())) {
            mValueTextView.setVisibility(VISIBLE);
            mValueTextView.setText(itemValue);
        } else {
            mValueTextView.setVisibility(GONE);
        }
    }

    public void setColoredItemSummary(String itemSummary) {
        mSummaryTextView.setTextColor(Color.RED);
        setItemSummary(itemSummary);
    }

    public void setItemSummary(String itemSummary) {
        if (itemSummary == null || itemSummary.isEmpty()) {
            mSummaryTextView.setVisibility(GONE);
        } else {
            mSummaryTextView.setText(itemSummary);
            mSummaryTextView.setVisibility(VISIBLE);
        }
    }

    public void setItemDrawable(Drawable itemDrawable) {
        if (itemDrawable == null) {
            mIconImageView.setVisibility(GONE);
        } else {
            mIconImageView.setImageDrawable(itemDrawable);
            mIconImageView.setVisibility(VISIBLE);
        }
    }

    public void setOnSwitchChecked(SwitchChecked onSwitchChecked) {
        this.onSwitchChecked = onSwitchChecked;
    }

    private enum DisplayMode {DefaultMode, SwitchMode}

    public interface SwitchChecked {
        void onSwitchChecked(boolean isChecked);
    }
}
