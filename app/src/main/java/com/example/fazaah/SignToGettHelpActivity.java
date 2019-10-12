package com.example.fazaah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignToGettHelpActivity extends AppCompatActivity {
    private SigninFragment mSigninFragment;
    private SignupNFragment mSignupNFragment;
    private Button mSigninButton;
    private Button mSignupButton;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_to_get_helpp);
        mSigninFragment =new SigninFragment();
        mSignupNFragment =new SignupNFragment();
        mSigninButton=findViewById(R.id.signinButton);
        mSignupButton=findViewById(R.id.signupButton);
        mContext= SignToGettHelpActivity.this;
        setFragment(mSigninFragment);
        mSigninButton.setBackgroundResource(R.drawable.background_sign_button_on);

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(mSigninFragment);
                mSigninButton.setBackgroundResource(R.drawable.background_sign_button_on);
                mSignupButton.setBackgroundResource(R.drawable.background_sign_button_off);
            }
        });

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(mSignupNFragment);
                mSigninButton.setBackgroundResource(R.drawable.background_sign_button_off);
                mSignupButton.setBackgroundResource(R.drawable.background_sign_button_on);
            }
        });


    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame,fragment);
        fragmentTransaction.commit();
    }
}
