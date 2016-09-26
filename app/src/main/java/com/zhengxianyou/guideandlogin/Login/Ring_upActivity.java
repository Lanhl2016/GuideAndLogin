package com.zhengxianyou.guideandlogin.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhengxianyou.guideandlogin.R;

/**
 * Fragmemt的宿主Activity
 * 包含：Login_Fragment，Register_Fragment，Forget_Fragment三个Fragment;
 */
public class Ring_upActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_up);
        init();
    }

    /**
     * 把Fragment放进宿主Activity；
     */
    private void init() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_ring_up_container,new Login_Fragment())
                .commit();
    }
}
