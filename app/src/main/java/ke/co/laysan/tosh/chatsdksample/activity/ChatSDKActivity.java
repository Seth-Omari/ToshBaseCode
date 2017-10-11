/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.laysan.tosh.chatsdksample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import ke.co.laysan.tosh.R;
import ke.co.laysan.tosh.activity.BaseActivity;
import ke.co.laysan.tosh.chatsdksample.fragment.ConversationsFragment;

public class ChatSDKActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_sdk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentsContainer, ConversationsFragment.newInstance(),
                        ConversationsFragment.class.getSimpleName())
                .commit();

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ChatSDKActivity.class);
        context.startActivity(starter);
    }
}
