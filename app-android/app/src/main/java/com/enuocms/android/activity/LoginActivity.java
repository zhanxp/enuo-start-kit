package com.enuocms.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.enuocms.android.R;
import com.enuocms.android.entity.UserInfo;
import com.enuocms.android.service.AdminService;
import com.enuocms.android.service.entity.AjaxResult;
import com.enuocms.android.service.handler.AjaxObjectDataHandler;
import com.enuocms.android.utils.SuperToastHelper;
import com.enuocms.android.widget.U_BaseActivity;

/**
 * @author 占小平
 * @function
 * @date 2017/9/17
 */
public class LoginActivity extends U_BaseActivity {

    protected AdminService adminService = new AdminService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LoginActivity _this = this;
        this.aq.id(R.id.btn_login).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = _this.aq.id(R.id.editUserName).getText().toString();
                String password = _this.aq.id(R.id.editPasswrod).getText().toString();
                showLoading();
                adminService.login(userName, password, new AjaxObjectDataHandler<UserInfo>() {
                    @Override
                    public void callback(AjaxResult result, UserInfo data) {
                        hideLoading();
                        if(result.success){
                            UserInfo.setInstance(data);
                            _this.goMain();
                        }else{
                            SuperToastHelper.e("登录失败！");
                        }
                    }
                });
            }
        });
    }

    private void  goMain(){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
