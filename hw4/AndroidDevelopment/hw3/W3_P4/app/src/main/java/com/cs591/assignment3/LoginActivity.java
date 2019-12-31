package com.cs591.assignment3;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLoginSubmit;

    private EditText etLoginUser;
    private EditText etLoginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLoginSubmit = findViewById(R.id.btnLoginSubmit);
        btnLoginSubmit.setOnClickListener(e -> toGame());

        etLoginUser = findViewById(R.id.etLoginUser);
        etLoginPass = findViewById(R.id.etLoginPass);
    }

    public void toGame() {
        if (etLoginUser.getText().toString().equals( "admin") && etLoginPass.getText().toString().equals("123")) {
//            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            Intent intent = GameActivity.newIntent(LoginActivity.this, "admin");
            startActivity(intent);
        } else  {
            Toast toast = Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 100);
            toast.show();
        }
    }
}
