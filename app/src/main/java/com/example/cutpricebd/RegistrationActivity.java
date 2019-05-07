package com.example.cutpricebd;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationActivity extends AppCompatActivity {
     private IClientServer iClientServer;
    TextInputEditText nameET,addET,emailET,phoneET,passET;
    AppCompatButton saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameET=findViewById(R.id.textInputEditTextName);
        addET=findViewById(R.id.textInputEditTextAddress);
        emailET=findViewById(R.id.textInputEditTextEmail);
        phoneET=findViewById(R.id.textInputEditTextPhone);
        passET=findViewById(R.id.textInputEditTextPassword);
       saveBtn =findViewById(R.id.appCompatButtonRegister);

       iClientServer=Retrofit_Api.getRetrofitInstance().create(IClientServer.class);


       saveBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               postApiToServer();
           }
       });


    }

    private void postApiToServer() {
        String name=nameET.getText().toString();
        String add=addET.getText().toString();
        String email=emailET.getText().toString();
        String phone=phoneET.getText().toString();
        String pass=passET.getText().toString();
        Call<User> responseCall=iClientServer.getApi( new User(name,add,email,phone,pass));

        responseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                User user=response.body();
                if (response.isSuccessful() && user != null) {
                    Toast.makeText(RegistrationActivity.this, ""+user.getName(),
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrationActivity.this,
                            String.format("Response is %s", String.valueOf(response.code()))
                            , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this,"eroor"+t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
