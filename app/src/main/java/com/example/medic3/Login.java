package com.example.medic3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
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
import com.backendless.persistence.local.UserIdStorageFactory;


public class Login extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etEmaill, etPassl;
    Button btnLogin, btnRegister;
    TextView tvReset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        etEmaill=findViewById(R.id.etMail);
        etPassl=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        tvReset=findViewById(R.id.tvReset);

        showProgress(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etEmaill.getText().toString().isEmpty()|| etPassl.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                } else
                {
                    String email=etEmaill.getText().toString().trim();
                    String pass=etPassl.getText().toString().trim();

                    showProgress(true);
                    tvLoad.setText("Busy logining you in... please wait");
                    Backendless.UserService.login(email, pass, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            Toast.makeText(Login.this, "Logined Succsessfully", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Login.this, homapageactivity.class));
                            Login.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(Login.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }
                    }, true);

                }

            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmaill.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                }else
                    {
                        String email=etEmaill.getText().toString().trim();
                        tvLoad.setText("Busy sending reset instructions");
                        showProgress(true);
                        Backendless.UserService.restorePassword(email, new AsyncCallback<Void>() {
                            @Override
                            public void handleResponse(Void response) {

                                Toast.makeText(Login.this, "Reset instructions sent to email address", Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(Login.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });
                    }
            }
        });




        Backendless.UserService.isValidLogin(new AsyncCallback<Boolean>() {
            @Override
            public void handleResponse(Boolean response) {

                if(response)
                {
                    tvLoad.setText("logining you in... please wait");
                    String userobjectID= UserIdStorageFactory.instance().getStorage().get();
                    Backendless.Data.of(BackendlessUser.class).findById(userobjectID, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            startActivity(new Intent(Login.this, homapageactivity.class));
                            Login.this.finish();

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Login.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    });

                }else
                    {
                        showProgress(false);
                    }

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(Login.this, "Error"+fault.getMessage(), Toast.LENGTH_SHORT).show();

                showProgress(false);

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





