package com.vinihans.persons_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.vinihans.persons_app.R;
import com.vinihans.persons_app.model.people.Address;
import com.vinihans.persons_app.model.people.People;
import com.vinihans.persons_app.view_model.people.PeopleViewModel;
import com.vinihans.persons_app.view_model.people.PeopleViewModelFactory;
import com.vinihans.persons_app.view_model.validations.FieldValidationStrategy;
import com.vinihans.persons_app.view_model.validations.NonEmptyValidationStrategy;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG = "REGISTERACTIVITY";
    private PeopleViewModel peopleViewModel;

    private TextInputEditText birthdayInputEditText;
    private EditText nameEditText;
    private EditText streetEditText;
    private EditText numberEditText;
    private EditText complementEditText;
    private EditText neighborhoodEditText;
    private EditText cityEditText;
    private EditText zipCodeEditText;
    private EditText stateEditText;
    private EditText phoneEditText;
    private EditText cpfEditText;

    private Button buttonRegister;
    private String token;

    private Date dataDeAniversario;

    private Map<EditText, FieldValidationStrategy> fieldValidationMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PeopleViewModelFactory viewModelFactory = new PeopleViewModelFactory(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        peopleViewModel = new ViewModelProvider(this,viewModelFactory).get(PeopleViewModel.class);
        Intent intent = getIntent();
        this.token = intent.getStringExtra("token");
        prepareScreen();
    }

    private void initializeValidation(List<EditText> editTextList){
        fieldValidationMap = new HashMap<>();
        for (EditText editText : editTextList){
            fieldValidationMap.put(editText, new NonEmptyValidationStrategy());
        }
    }

    private void prepareScreen(){
        this.nameEditText = findViewById(R.id.editTextName);

        this.birthdayInputEditText = findViewById(R.id.editTextBirthday);
        this.birthdayInputEditText.setOnClickListener(view-> showDatePickerDialog());

        this.streetEditText = findViewById(R.id.editTextStreet);
        this.numberEditText = findViewById(R.id.editTextNumber);
        this.complementEditText = findViewById(R.id.editTextComplement);
        this.neighborhoodEditText = findViewById(R.id.editTextNeighborhood);
        this.cityEditText = findViewById(R.id.editTextCity);
        this.zipCodeEditText = findViewById(R.id.editTextZipCode);
        this.stateEditText = findViewById(R.id.editTextState);
        this.phoneEditText = findViewById(R.id.editTextPhone);
        this.cpfEditText = findViewById(R.id.editTextCPF);

        this.buttonRegister = findViewById(R.id.buttonRegister);
        this.buttonRegister.setOnClickListener(view -> {
            registrar();
        });
        List<EditText> texts = new ArrayList<>(Arrays.asList(nameEditText,
                streetEditText, numberEditText,
                cityEditText, zipCodeEditText,
                stateEditText, phoneEditText,
                cpfEditText));
        initializeValidation(texts);
    }

    private void registrar() {
        boolean allFieldsValid = true;

        for (Map.Entry<EditText,FieldValidationStrategy> entry : this.fieldValidationMap.entrySet()){
            EditText editText = entry.getKey();
            FieldValidationStrategy validationStrategy = entry.getValue();

            if (!validationStrategy.isValid(editText)){
                editText.setError("Campo não pode ser vazio");
                allFieldsValid = false;
            }
        }

        if (allFieldsValid) {

            if (dataDeAniversario != null) {

                People people = new People(this.nameEditText.getText().toString(), this.cpfEditText.getText().toString(),
                        dataDeAniversario, new Address(this.streetEditText.getText().toString(),
                        this.numberEditText.getText().toString(),
                        this.complementEditText.getText().toString(),
                        this.neighborhoodEditText.getText().toString(),
                        this.cityEditText.getText().toString(),
                        this.zipCodeEditText.getText().toString(),
                        this.stateEditText.getText().toString())
                        , this.phoneEditText.getText().toString());

                peopleViewModel.createOne(people, this.token);
            }
        }
            }


    private void showDatePickerDialog(){
        final Calendar calendar = Calendar.getInstance();
        int year =  calendar.get(Calendar.YEAR);
        int month =  calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, i, i1, i2) -> {
                    calendar.set(Calendar.YEAR, i);
                    calendar.set(Calendar.MONTH, i1);
                    calendar.set(Calendar.DAY_OF_MONTH,i2 );

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    String formattedDate = sdf.format(calendar.getTime());
                    this.birthdayInputEditText.setText(formattedDate);

                        try {
                            dataDeAniversario = sdf.parse(formattedDate);

                            Log.e(TAG, "showDatePickerDialog: porraaa  " +dataDeAniversario );
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }


                },
                year,month,dayOfMonth
        );


        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

}