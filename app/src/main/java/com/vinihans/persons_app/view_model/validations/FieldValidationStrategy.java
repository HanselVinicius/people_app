package com.vinihans.persons_app.view_model.validations;

import android.widget.EditText;

public interface FieldValidationStrategy {
    boolean isValid(EditText editText);
}
