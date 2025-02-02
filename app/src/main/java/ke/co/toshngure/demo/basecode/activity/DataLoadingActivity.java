/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.demo.basecode.activity;

import android.os.Bundle;

import ke.co.toshngure.basecode.R;;
import ke.co.toshngure.demo.basecode.fragment.UsersFragment;

public class DataLoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_loading);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentsContainer, UsersFragment.newInstance())
                .commit();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
