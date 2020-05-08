package com.example.cs125dto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private SqliteDTO sqliteDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup);
        Button finishBtn = findViewById(R.id.finishButton);
        final EditText Age = findViewById(R.id.userAge);
        final EditText Gender = findViewById(R.id.userGender);
        final EditText Height = findViewById(R.id.userHeight);
        final EditText Weight = findViewById(R.id.userWeight);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("age",Age.getText().toString());
                intent.putExtra("gender",Gender.getText().toString());
                intent.putExtra("height",Height.getText().toString());
                intent.putExtra("weight",Weight.getText().toString());
                startActivity(intent);
            }
        });

    }
}
