package com.example.medic3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class Register extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etName, etEmail,etPass, etRepass;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);
        etName=findViewById(R.id.etNamel);
        etEmail=findViewById(R.id.etEmail);
        etPass=findViewById(R.id.etPass);
        etRepass=findViewById(R.id.etRepass);
        btnRegister=findViewById(R.id.btnRegisterR);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()|| etEmail.getText().toString().isEmpty()
                        ||etPass.getText().toString().isEmpty()||etRepass.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Pleae fill all fields", Toast.LENGTH_SHORT).show();

                }else{
                    if(etPass.getText().toString().equals(etRepass.getText().toString()))
                    {
                        String Name=etName.getText().toString().trim();
                        String Email=etEmail.getText().toString().trim();
                        String pass=etPass.getText().toString().trim();
                        String repass=etRepass.getText().toString().trim();

                        BackendlessUser user=new BackendlessUser();
                        user.setEmail(Email);
                        user.setPassword(pass);
                        user.setProperty("name", Name);

                        showProgress(true);
                        tvLoad.setText("Busy registering user....please wait");

                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                showProgress(false);
                                Toast.makeText(Register.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                                Register.this.finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(Register.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);

                            }
                        });


                    }else{
                        Toast.makeText(Register.this, "Repassword field doesn't match passwordfield", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}