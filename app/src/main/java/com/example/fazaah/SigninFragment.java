package com.example.fazaah;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SigninFragment extends Fragment {

    private EditText mEmailText;
    private EditText mPasswordText;
    private Button mSigninButton;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private Window mWindow;


    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        mEmailText =view.findViewById(R.id.emailText);
        mPasswordText = view.findViewById(R.id.passwordText);
        mSigninButton = view.findViewById(R.id.signinButton);
        mProgressBar = view.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extracting the pure text
                final String email = mEmailText.getText().toString();
                String password = mPasswordText.getText().toString();
                //checking if the extracted email and password are not empty
                // then make the progress bar visible
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mSigninButton.setVisibility(View.GONE);
                    //comparing the inputs with the data in firebase then checking correctness
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Intent intent =new Intent(getContext(),MainVolentierActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                                Vibrator vibrator=(Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(100);

                            }
                            mProgressBar.setVisibility(View.GONE);
                            mSigninButton.setVisibility(View.VISIBLE);
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "الرجاء ادخال جميع البيانات", Toast.LENGTH_LONG).show();
                    Vibrator vibrator=(Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                }
            }
        });

        return view;
    }
}