package com.zhengxianyou.guideandlogin.Login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhengxianyou.guideandlogin.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册界面
 */

public class Register_Fragment extends Fragment implements View.OnClickListener {
    private View mView;

    private EditText et_login,et_password,et_repassword,et_email;
    private Button btn_logup;
    public   String email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_register, container, false);

        initView();
        return mView;
    }

    /**
     * 初始化view
     */
    private void initView() {
        et_login = (EditText) mView.findViewById(R.id.et_login);
        et_email = (EditText) mView.findViewById(R.id.et_email);
        et_password = (EditText) mView.findViewById(R.id.et_password);
        et_repassword = (EditText) mView.findViewById(R.id.et_repassword);

        btn_logup = (Button) mView.findViewById(R.id.btn_logup);
        btn_logup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logup:
                String login = et_login.getText().toString();
                String password = et_password.getText().toString();
                String repassword = et_repassword.getText().toString();

                if (!TextUtils.isEmpty(login)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(repassword)){
                    if (password.equals(repassword)){
                        register(login,password);
                    }else {
                        toast("您输入的密码不一致");
                    }

                }else {

                    toast("输入框不能为空");
                }

                break;
        }
    }

    /**
     *注册相关逻辑
     */
    private void register(String login,String password) {
        MyUser user = new MyUser();
        email = et_email.getText().toString();
        user.setEmail(email);

        user.setUsername(login);
        user.setPassword(password);

//        try {

            user.signUp(new SaveListener<MyUser>() {
                @Override
                public void done(MyUser myUser, BmobException e) {
                    if (e == null){
                        toast("注册成功");
                        getActivity().finish();
                    }else {

                     int error = e.getErrorCode();
                        toast("e"+error);}
                }
            });

//        }catch (Exception exception){

//        }
    }

    /**
     *封装一个Toast,减少代码重复
     */
    private void toast(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();

    }

}
