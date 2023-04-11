package com.example.plantingyourway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextInputEditText etEmail;
    TextInputEditText etPassword;
    TextView tvSignUp;
    MaterialButton btnLogin;

    CheckBox cbRememberMe;
    DBManager dbManager;

    String email = "", password = "";
    Boolean loginEnabled = false;
    CallbackManager callbackManager;

    LoginButton loginButton;
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
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeUI();

    }

    private void initializeUI() {
        tilEmail = findViewById(R.id.txtInputLayoutEmail);
        tilPassword = findViewById(R.id.txtInputLayoutPassword);
        etEmail = findViewById(R.id.editTxtEmail);
        etPassword = findViewById(R.id.editTxtPassword);
        tvSignUp = findViewById(R.id.txtViewSignup);

        btnLogin = findViewById(R.id.btnLogin);
        cbRememberMe = findViewById(R.id.chechBoxRememberMe);

        tvSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        setUpTextWatcher();
        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
//        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // Handle successful login
                Log.e("lginResult_callback", loginResult.toString());
                fbDataCollection(loginResult);
            }

            @Override
            public void onCancel() {
                // Handle canceled login
            }

            @Override
            public void onError(FacebookException exception) {
                // Handle error during login
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("lginResult", loginResult.toString());
                        fbDataCollection(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.e("lginResult_cancel", "(");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("lginResult_exception", exception.toString());
                    }
                });


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Log.e("isloggedid", "" + isLoggedIn);
        if (isLoggedIn) {
            Log.e("isloggedid_accesstoken", "" + accessToken.getToken());
            Log.e("isloggedid_accesstoken", "" + accessToken.getApplicationId());
            Log.e("isloggedid_accesstoken", "" + accessToken.getUserId());
            Log.e("isloggedid_accesstoken", "" + accessToken);

        }

    }

    private void fbDataCollection(LoginResult loginResult) {
        AccessToken accessToken = loginResult.getAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String email = object.getString("email");
                            String id = object.optString("id");
                            String firstName = object.optString("first_name");
                            String lastName = object.optString("last_name");
                            String name = object.optString("name");
                            String profileImageUrl = "https://graph.facebook.com/" + id + "/picture?type=large";

                            Log.e("loggedInId", id);
                            Log.e("loggedInFirstName", firstName);
                            Log.e("loggedInLastName", lastName);
                            Log.e("loggedInName", name);
                            Log.e("loggedInProfilePicUrl", profileImageUrl);

                            // Using the user's email address here
                            String finalName = "";
                            if (name.equals("")) {
                                finalName = email.split("@")[0];
                            } else {
                                finalName = name;
                            }

                            if (dbManager.insertUser(new UserDetailsDataModel(finalName, "N.A", "N.A", "N.A", email, "N.A@A019", "N.A")) != -1) {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                cbRememberMe.setChecked(true);
                                saveDataToPref(new UserDetailsDataModel(
                                        finalName, "N.A", "N.A", "N.A", email, "N.A@A019", "N.A"

                                ));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setUpTextWatcher() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                validateUserInputs();
            }
        });
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                email = s.toString();
                Log.e("fdffdfdf", email);
                validateUserInputs();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbManager = new DBManager(this);
        dbManager.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
    }

    private void validateUserInputs() {

        if (email.equals("") && password.equals("")) {
            loginEnabled = false;
            tilEmail.setError("Email cannot be empty");
            tilPassword.setError("Password cannot be empty");
        } else if (email.equals("")) {
            loginEnabled = false;
            tilEmail.setError("Email cannot be empty");
        } else if (password.equals("")) {
            loginEnabled = false;
            tilPassword.setError("Password cannot be empty");
        } else if (!email.equals("") && !password.equals("")) {
            tilEmail.setError(null);
            tilPassword.setError(null);

            if (validateEmail() && validatePassword()) {
                loginEnabled = true;
                btnLogin.setEnabled(true);
            } else {
                loginEnabled = false;
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtViewSignup:
                startActivity(new Intent(this, RegistrationActivity.class));
                break;
            case R.id.btnLogin:
                validateUserLogin();

                break;
        }
    }


    private void validateUserLogin() {


        Cursor cursor = dbManager.fetchUserDetails(email, password);
        Log.e("do_ititi", "lets ooit");
        if (cursor != null && cursor.getCount() > 0) {
            //looping through all the records
            do {
                saveDataToPref(new UserDetailsDataModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));
                Toast.makeText(this, "User Login Successful", Toast.LENGTH_LONG).show();
                Log.e("ursonr-data", "" + cursor.getString(0));
                Log.e("ursonr-data", cursor.getString(1));
                Log.e("ursonr-data", cursor.getString(2));

            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "No Records Exist. Check Email Password", Toast.LENGTH_LONG).show();
        }
    }

    private void saveDataToPref(UserDetailsDataModel serviceProviderModel) {
        SharedPref.writeBooleanValue(Constants.REMEMBER_ME, cbRememberMe.isChecked());
        SharedPref.writeLoggedUserDetails(Constants.USER_DETAILS, serviceProviderModel);
        Log.e("isServiceProerChecked", "false");
        startActivity(new Intent(this, MainActivity.class));
        finish();
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

    public void signIn(View view) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

}