package com.vinihans.persons_app.view_model.validations;

import android.text.TextUtils;
import android.widget.EditText;

public class NonEmptyValidationStrategy implements FieldValidationStrategy{
    @Override
    public boolean isValid(EditText editText) {
        return !editText.getText().toString().isBlank();
    }
}
