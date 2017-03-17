package com.google.volley_reglogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by rayleigh on 3/15/17.
 */

public class ActivityUserProfile extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        textView.setText("Welcome User " + intent.getStringExtra(LoginActivity.KEY_USERNAME));
    }
}
