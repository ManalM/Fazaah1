package com.example.fazaah;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class SignupVFragment extends Fragment {
    private EditText mEmailText;
    private EditText mPasswordText;
    private EditText mConfirmPasswordText;
    private EditText mNameText;
    private EditText mphoneText;
    private Button mSignupButton;
    private ProgressBar mProgressBar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;


    public SignupVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_v, container, false);
        mEmailText =  view.findViewById(R.id.emailText);
        mPasswordText = view.findViewById(R.id.passwordText);
        mConfirmPasswordText =  view.findViewById(R.id.confirmPasswordText);
        mNameText =  view.findViewById(R.id.nameText);
        mphoneText =  view.findViewById(R.id.phoneText);
        mSignupButton = view.findViewById(R.id.signupButton);
        mProgressBar = view.findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extracting the pure text
                /*final String email = mEmailText.getText().toString();
                final String password = mPasswordText.getText().toString();
                String confirmPassword = mConfirmPasswordText.getText().toString();
                final String name = mNameText.getText().toString();
                final String number = mphoneText.getText().toString();*/
                final String email = mEmailText.getText().toString();
                final String password = mPasswordText.getText().toString();
                String confirmPassword = mConfirmPasswordText.getText().toString();
                final String name = mNameText.getText().toString();
                final String number = mphoneText.getText().toString();
                //checking if the extracted email and password are not empty
                // then make the progress bar visible
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) & !TextUtils.isEmpty(confirmPassword)
                        & !TextUtils.isEmpty(name)& !TextUtils.isEmpty(number)) {
                    if (password.equals(confirmPassword)) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        mSignupButton.setVisibility(View.GONE);
                        //creating email and password data in firebase then checking success
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser mUser=FirebaseAuth.getInstance().getCurrentUser();

                                    Accounts accounts;

                                    accounts= new Accounts(name, number, email,"Empty","Fzeee");

                                    mDatabase.getReference("Accounts").child(mUser.getUid()).setValue(accounts).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getContext(), "تم إنشاء الحساب بنجاح", Toast.LENGTH_SHORT).show();
                                            mProgressBar.setVisibility(View.GONE);
                                            mSignupButton.setVisibility(View.VISIBLE);
                                            Intent intent=new Intent(getContext(),MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            mProgressBar.setVisibility(View.GONE);
                                            mSignupButton.setVisibility(View.VISIBLE);
                                            String error = e.getMessage();
                                            Toast.makeText(getContext(), "Error is: " + error, Toast.LENGTH_LONG).show();
                                            Vibrator vibrator=(Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                            vibrator.vibrate(100);
                                        }
                                    });

                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getContext(), "Error is: " + error, Toast.LENGTH_LONG).show();
                                    mProgressBar.setVisibility(View.GONE);
                                    mSignupButton.setVisibility(View.VISIBLE);
                                    Vibrator vibrator=(Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                    vibrator.vibrate(100);
                                }
                            }
                        });


                    } else {
                        Toast.makeText(getContext(), "كلمة المرور غير متطابقة", Toast.LENGTH_LONG).show();
                        Vibrator vibrator=(Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);

                    }

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
