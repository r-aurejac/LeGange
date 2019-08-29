package com.example.legange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button jouerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jouerButton = (Button) findViewById(R.id.jouer_button);
        jouerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent intent = AddPlayerActivity.newIntent(getApplicationContext());
                getApplicationContext().startActivity(intent);
            }
        });

    }
}
