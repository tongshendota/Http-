package com.example.pp03.httputils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pp03 on 2018-01-09.
 */

public class Login extends Activity implements LoginView{

    Button login;
    LoginPer i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
        login =(Button)findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i.dologin("tong2","123456");
                }
            });
        i =new  LoginImp(this,Login.this);
    }

    @Override
    public void loginresutle(int code, List<DataBean> data) {
        if(code==200){
                login.setText(data.get(0).getUrl());
        }
    }
}
