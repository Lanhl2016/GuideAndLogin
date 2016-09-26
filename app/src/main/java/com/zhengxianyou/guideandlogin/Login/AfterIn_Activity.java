package com.zhengxianyou.guideandlogin.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhengxianyou.guideandlogin.R;

/**
 * 登录后的界面，仅用于测试，可自己提换成需要的Activity
 */
public class AfterIn_Activity extends AppCompatActivity {

    private TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterin);
        initView();
    }

    private void initView() {
        tv_login = (TextView) findViewById(R.id.tv_login);

        Intent i = getIntent();
       String name = i.getStringExtra("username");
        tv_login.setText("Hello :"+name);
    }
}
