package com.google.volley_reglogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rayleigh on 3/15/17.
 */

public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_URL = "http://104.196.62.142:8080/server_1/webapi/users/login";

    public static final String KEY_EMAIL="email";
    public static final String KEY_PASSWORD="password";

    private static final String TAG = "Login Acitivity";

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void userLogin() {
        email = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        String mURL = LOGIN_URL + '?';

        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle access token.
                        Log.e(TAG, "Login received: " + response);
                        long token = Long.parseLong(response);
                        if(token == 0) {
                            Toast.makeText(LoginActivity.this, R.string.loginfail_toast, Toast.LENGTH_LONG).show();
                        } else {
                            openProfile();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error message: " + error.toString());
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.e(TAG, stringRequest.toString());
    }

    private void openProfile(){
        Intent intent = new Intent(this, ActivityUserProfile.class);
        intent.putExtra(KEY_EMAIL, email);
        startActivity(intent);
    }

}
