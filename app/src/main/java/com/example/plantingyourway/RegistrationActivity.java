package com.example.plantingyourway;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTxtName, editTxtEmail, editTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("Planting Your Way");


        initializeUi();
    }

    private void initializeUi() {
        tilEmail = findViewById(R.id.txtInputLayoutEmail);
        tilPassword = findViewById(R.id.txtInputLayoutPassword);
        tilName = findViewById(R.id.txtInputLayoutName);
        tilAddress = findViewById(R.id.txtInputLayoutAddress);
        tilCity = findViewById(R.id.txtInputLayoutProvince);
        tilProvince = findViewById(R.id.txtInputLayoutCity);
        tilPhone = findViewById(R.id.txtInputLayoutPhone);


        etEmail = findViewById(R.id.editTxtEmail);
        etPassword = findViewById(R.id.editTxtPassword);
        etName = findViewById(R.id.editTextName);
        etAddress = findViewById(R.id.editTxtAddress);
        etPhone = findViewById(R.id.editTxtPhone);
        actvProvince = findViewById(R.id.AutoCompleteTxtViewProvince);
        actvCity = findViewById(R.id.AutoCompleteTxtViewCity);

        tvLogin = findViewById(R.id.txtViewLogin);
        btnRegister = findViewById(R.id.btnRegister);

        tvLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        initDb();
        setUpTextWatchers();
        setUpSpinner();
    }

    public void signUp(View view){

        String userName = editTxtName.getText().toString();
        String userEmail = editTxtEmail.getText().toString();
        String userPassword = editTxtPassword.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Please enter the name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Please enter the email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Please enter the Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 8){
            Toast.makeText(this, "Password too short... Enter a password of 8 or more characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }
    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextInputLayout tilName;
    TextInputLayout tilAddress;
    TextInputLayout tilCity;
    TextInputLayout tilProvince;
    TextInputLayout tilPhone;

    TextInputEditText etEmail;
    TextInputEditText etPassword;
    TextInputEditText etName;
    TextInputEditText etAddress;
    TextInputEditText etPhone;
    AutoCompleteTextView actvProvince;
    AutoCompleteTextView actvCity;

    TextView tvLogin;
    MaterialButton btnRegister;

    CheckBox cbSignUpAsProvider;

    String name = "", email = "", password = "", address = "", selectedCity = "", selectedProvince = "", phone = "";
    Boolean registrationEnable = false;
    DBManager dbManager = null;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            validateInputs();
        }
    };

    public void validateInputs() {
        try {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            address = etAddress.getText().toString();
//           selectedCity= etEmail.getText().toString();
//           selectedProvince= etEmail.getText().toString();
            Log.e("name_",name);
            Log.e("email",email);
            Log.e("passwr",password);
            Log.e("phone",phone);
            Log.e("address",address);
            Log.e("selectedcity",selectedCity);
            Log.e("province",selectedProvince);
            if (!email.equals("") && !password.equals("") && !name.equals("") && !address.equals("") && !phone.equals("") && !selectedCity.equals("") && !selectedProvince.equals("")) {
                tilEmail.setError(null);
                tilName.setError(null);
                tilPassword.setError(null);
                tilPhone.setError(null);
                tilAddress.setError(null);


                registrationEnable= validateEmail() && validatePassword();
            }  else {
                registrationEnable=false;
                if (email.equals("")) {
                    tilEmail.setError("Email cannot be empty");
                }else
                {
                    tilEmail.setError(null);
                }
                if (password.equals("")) {
                    tilPassword.setError("Password cannot be empty");
                }else{
                    tilPassword.setError(null);
                }
                if (name.equals("")) {
                    tilName.setError("Name cannot be empty");
                }else{
                    tilName.setError(null);
                }
                if (phone.equals("")) {
                    tilPhone.setError("Phone No. cannot be empty");
                }else{
                    tilPhone.setError(null);
                }
                if (address.equals("")) {
                    tilAddress.setError("Address cannot be empty");
                }else{
                    tilAddress.setError(null);
                }

            }
            btnRegister.setEnabled(registrationEnable);
        } catch (Exception e) {
            registrationEnable = false;
            btnRegister.setEnabled(registrationEnable);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
//                UtilityFunctions.getInstance().CloseKeyBoard(this);
                if (dbManager.insertUser(new UserDetailsDataModel(name,address,selectedCity,selectedProvince,email,password,phone)) != -1) {
                    Toast.makeText(this, "Registration successful. Please Sign in.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed. Please Try Again later", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.txtViewLogin:
                finish();
                break;
        }
    }

    private void initDb() {
        dbManager = new DBManager(this);
        dbManager.open();
    }


    String[] cities = null;

    private void setUpSpinner() {
        //We will use this data to inflate the drop-down items
        String[] provinces = getResources().getStringArray(R.array.provinces);

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, provinces);
        actvProvince.setAdapter(provinceAdapter);

        //to get selected value add item click listener
        actvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "" + actvProvince.getText().toString(), Toast.LENGTH_SHORT).show();
                selectedProvince = actvProvince.getText().toString();

                switch (actvProvince.getText().toString()) {
                    case "Alberta":
                        cities = getResources().getStringArray(R.array.alberta_cities);
                        break;
                    case "BC":
                        cities = getResources().getStringArray(R.array.bc_cities);
                        break;
                    case "Manitoba":
                        cities = getResources().getStringArray(R.array.manitoba_cities);
                        break;
                    case "Nova Scotia":
                        cities = getResources().getStringArray(R.array.nova_scotia_cities);
                        break;
                    case "Ontario":
                        cities = getResources().getStringArray(R.array.ontario_cities);
                        break;
                    case "Quebec":
                        cities = getResources().getStringArray(R.array.quebec_cities);
                        break;
                }


                ArrayAdapter<String> cititesAdapter = new ArrayAdapter<String>(RegistrationActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, cities);
                actvCity.setAdapter(cititesAdapter);

                //to get selected value add item click listener
                actvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedCity = actvCity.getText().toString();
                        validateInputs();
                        Toast.makeText(getApplicationContext(), "" + actvCity.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void setUpTextWatchers() {

        etName.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etEmail.addTextChangedListener(textWatcher);
        etPhone.addTextChangedListener(textWatcher);
        etAddress.addTextChangedListener(textWatcher);
        actvCity.addTextChangedListener(textWatcher);
        actvProvince.addTextChangedListener(textWatcher);


    }

    private boolean validateEmail() {
        String emailInput = etEmail.toString().trim();

        if (emailInput.isEmpty()) {
            tilEmail.setError("Field can't be empty");
            return false;
        } else if (!email.matches(emailPattern)) {
            tilEmail.setError("Please enter a valid email address");
            return false;
        } else {
            tilEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = etPassword.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            tilPassword.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            tilPassword.setError("Password too weak");
            return false;
        } else {
            tilPassword.setError(null);
            return true;
        }
    }




}