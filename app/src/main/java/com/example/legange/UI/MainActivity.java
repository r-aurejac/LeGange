package com.example.legange.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.legange.R;

public class MainActivity extends AppCompatActivity {

    private Button jouerButton;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

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
