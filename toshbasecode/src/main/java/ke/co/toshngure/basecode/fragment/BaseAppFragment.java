/*
 * Copyright (c) 2017.
 *
 * Anthony Ngure
 *
 * Email : anthonyngure25@gmail.com
 */

package ke.co.toshngure.basecode.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Anthony Ngure on 19/09/2017.
 * Email : anthonyngure25@gmail.com.
 */

public class BaseAppFragment extends Fragment {

    public static BaseAppFragment newInstance() {

        Bundle args = new Bundle();

        BaseAppFragment fragment = new BaseAppFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void startActivity(Intent intent) {
        //super.startActivity(intent);
        getActivity().startActivity(intent);
    }
}
