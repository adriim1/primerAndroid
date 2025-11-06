package com.adriim1.first;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    private FormUtils formUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        formUtils = new FormUtils();

        TextInputLayout loginTILUserName = findViewById(R.id.loginTILuserName);
        TextInputLayout loginTILPassword = findViewById(R.id.loginTILpassword);
        Button loginButton = findViewById(R.id.loginButton);
        Button loginBtnRegister = findViewById(R.id.loginBtnRegister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = formUtils.getTILText(loginTILUserName);
                String password = formUtils.getTILText(loginTILPassword);

                SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                String savedUsername = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");

                if (!savedUsername.isEmpty() && formUtils.checkPassword(password, savedPassword)) {
                    Intent intentMain = new Intent(Login.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username", username);
                    intentMain.putExtras(bundle);
                    startActivity(intentMain);
                } else {
                    Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}