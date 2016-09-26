package com.zhengxianyou.guideandlogin.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhengxianyou.guideandlogin.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 前往Bmob官网注册key替换下面的key
 * application ID: 331124d90b4490ed166021f7cec2a50e
 */

public class Login_Fragment extends Fragment implements View.OnClickListener {
    private View v;

    private EditText et_login, et_password;
    private Button btn_logup, btn_login;
    private TextView tv_forget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login, container, false);
        //第一：默认初始化
        Bmob.initialize(getActivity(), "331124d90b4490ed166021f7cec2a50e");

        initView();
        return v;
    }

    /**
     * 初始化view
     */
    private void initView() {

        et_login = (EditText) v.findViewById(R.id.et_login);
        et_password = (EditText) v.findViewById(R.id.et_password);

        btn_logup = (Button) v.findViewById(R.id.btn_logup);
        btn_logup.setOnClickListener(this);

        btn_login = (Button) v.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        tv_forget = (TextView) v.findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_login:
                login();

                break;
            case R.id.btn_logup:
                //跳转到注册界面
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_ring_up_container, new Register_Fragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.tv_forget:
                //跳转到找回密码界面
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_ring_up_container,new Forget_Fragment())
                        .addToBackStack(null)
                        .commit();
                break;

        }
    }


    /**
     * 登录
     */
    String name, password;

    private void login() {
        name = et_login.getText().toString();
        password = et_password.getText().toString();
        final MyUser user = new MyUser();

        user.setUsername(name);
        user.setPassword(password);
        user.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                try {
                    if (user.getEmailVerified()) {
                        if (e == null) {
                            toast("登录成功:");
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                            Intent i = new Intent(getActivity(), AfterIn_Activity.class);
                            i.putExtra("username", name);
                            startActivity(i);
                            getActivity().finish();
                        } else {
                            toast("error");
                        }
                    } else {
                        toast("请前往邮箱验证");
                    }
                } catch (Exception exception) {
                    toast("正在登录");
                }
            }
        });

    }

    private void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();

    }
}
