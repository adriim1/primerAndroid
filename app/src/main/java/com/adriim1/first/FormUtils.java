package com.adriim1.first;

import com.google.android.material.textfield.TextInputLayout;
import org.mindrot.jbcrypt.BCrypt;

public class FormUtils {
    public boolean isTILEmpty(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString().trim().isEmpty();
    }

    public String getTILText(TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }

    public String generateHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}
