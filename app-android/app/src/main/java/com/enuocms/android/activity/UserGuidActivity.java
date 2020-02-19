package com.enuocms.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.enuocms.android.R;
import com.enuocms.android.entity.UserInfo;

/**
 * @author 占小平
 * @function
 * @date 2017/9/17
 */
public class UserGuidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userguid);
        new Handler().postDelayed(r, 1000);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent();
            if( UserInfo.isUserLogin()){
                intent.setClass(UserGuidActivity.this, MainActivity.class);
            }else{
                intent.setClass(UserGuidActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }
    };

//    protected  boolean checkUserLogin (){
//        UserInfo.isUserLogin()
//        Context ctx = UserGuidActivity.this;
//        SharedPreferences sp = ctx.getSharedPreferences("enuocms",MODE_PRIVATE);
//        String token = sp.getString("ticket","");
//        if(token.length() > 0){
//            return  true;
//        }else{
//            return  false;
//        }
//    }

}
