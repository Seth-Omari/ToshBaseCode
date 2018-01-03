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
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.co.toshngure.basecode.images.NetworkImage;
import ke.co.toshngure.demo.basecode.R;

public class NetworkImageActivity extends BaseActivity {

    @BindView(R.id.sourceTV)
    TextView sourceTV;
    @BindView(R.id.circledNI)
    NetworkImage circledNI;
    @BindView(R.id.normalNI)
    NetworkImage normalNI;
    private boolean hasBackground;

    public static void start(Context context) {
        Intent starter = new Intent(context, NetworkImageActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_image);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        circledNI.setLoadingCallBack(drawable -> toastDebug("Circle Image Loaded"));
        normalNI.setLoadingCallBack(drawable -> toastDebug("Normal Image Loaded"));
    }

    @OnClick(R.id.networkBtn)
    public void onNetworkBtnClicked() {
        sourceTV.setText("Network");
        Random random = new Random();
        circledNI.loadImageFromNetwork("https://lorempixel.com/400/400/transport/?" + random.nextInt());
        normalNI.loadImageFromNetwork("https://lorempixel.com/400/400/transport/?" + random.nextInt());
    }

    @OnClick(R.id.mediaStoreBtn)
    public void onMediaStoreBtnClicked() {
        sourceTV.setText("Media Store");
    }

    @OnClick(R.id.drawableBtn)
    public void onDrawableBtnClicked() {
        sourceTV.setText("Drawable");
        circledNI.setImageResource(R.drawable.ic_android_black_96dp);
        normalNI.setImageResource(R.drawable.ic_android_black_96dp);
    }

    @OnClick(R.id.backgroundBtn)
    public void onBackgroundBtnClicked() {
        if (hasBackground) {
            circledNI.setBackgroundColor(Color.TRANSPARENT);
            normalNI.setBackgroundColor(Color.TRANSPARENT);
        } else {
            circledNI.setBackgroundColor(Color.DKGRAY);
            normalNI.setBackgroundColor(Color.DKGRAY);
        }
        hasBackground = !hasBackground;
    }
}
