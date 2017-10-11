/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.laysan.tosh.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.co.laysan.tosh.R;
import ke.co.toshngure.basecode.view.BaseSimpleListItemView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.chatSDKSLI)
    BaseSimpleListItemView chatSDKSLI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.chatSDKSLI)
    public void onViewClicked() {
        /*new ConversationBuilder()
                .withUserName("Anthony Ngure")
                .withUserId(1)
                .withPartnerName("Carlistars Wanga")
                .withPartnerId(2)
                .startConversation(this);*/

    }
}
