package com.adriim1.first;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private FormUtils formUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        formUtils = new FormUtils();

        TextInputLayout registerTILUserName = findViewById(R.id.registerTILuserName);
        TextInputLayout registerTILEmail = findViewById(R.id.registerTILEmail);
        TextInputLayout registerTILPassword = findViewById(R.id.registerTILpassword);
        TextInputLayout registerTILConfirmPassword = findViewById(R.id.registerTILconfirmPassword);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = formUtils.getTILText(registerTILUserName);
                String email = formUtils.getTILText(registerTILEmail);
                String password = formUtils.getTILText(registerTILPassword);
                String confirmPassword = formUtils.getTILText(registerTILConfirmPassword);

                if (validateInput(username, email, password, confirmPassword)) {
                    String hashedPassword = formUtils.generateHashedPassword(password);
                    SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", hashedPassword);
                    editor.apply();

                    Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validateInput(String username, String email, String password, String confirmPassword) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Introduce un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}