package com.example.fazaah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    private Button mVolinteerButton;
    private Button mNeedHelpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        mVolinteerButton =findViewById(R.id.volinteerButton);
        mNeedHelpButton=findViewById(R.id.needHelpButton);

        mVolinteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ChooseActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mNeedHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ChooseActivity.this, SignToGettHelpActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
