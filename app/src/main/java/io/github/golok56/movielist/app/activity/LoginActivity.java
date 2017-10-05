package io.github.golok56.movielist.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.github.golok56.movielist.R;
import io.github.golok56.movielist.utility.PreferenceManager;

/**
 * @author Satria Adi Putra
 */
public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME = "satadii11";
    private static final String PASSWORD = "10116167";

    private EditText mEtUsername;
    private EditText mEtPassword;

    private PreferenceManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefManager = new PreferenceManager(this);
        if(mPrefManager.isLogin()){
            goToHomeActivity();
        }

        setContentView(R.layout.activity_login);

        mEtUsername = (EditText) findViewById(R.id.et_activity_login_username);
        mEtPassword = (EditText) findViewById(R.id.et_activity_login_password);

        findViewById(R.id.btn_activity_login_do_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    if(TextUtils.isEmpty(username)){
                        mEtUsername.setError(getString(R.string.should_not_be_empty_field));
                    }

                    if(TextUtils.isEmpty(password)){
                        mEtPassword.setError(getString(R.string.should_not_be_empty_field));
                    }
                    return;
                }

                String message;

                if (username.equals(USERNAME) && password.equals(PASSWORD)){
                    message = "Login berhasil";
                    mPrefManager.setLogin(true);
                    goToHomeActivity();
                } else {
                    message = "Username atau password salah. Silakan masukkan ulang!";
                }

                Toast.makeText(
                        LoginActivity.this,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void goToHomeActivity(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
